package com.plogging.domain.User.service.user;

import com.plogging.domain.Quest.dto.userQuestProceeding.request.CreateQuestProceedingReq;
import com.plogging.domain.Quest.service.questProceeding.QuestProceedingService;
import com.plogging.domain.User.dto.request.*;
import com.plogging.domain.User.dto.response.*;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.*;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.domain.User.service.userToken.UserRefreshTokenService;
import com.plogging.global.dto.ApplicationResponse;
import com.plogging.global.enumerations.PresenceStatus;
import com.plogging.global.jwt.service.JwtService;
import com.plogging.global.utill.SHA256Util;
import com.plogging.global.utill.imgae.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserRefreshTokenService userRefreshTokenService;
    private final QuestProceedingService questProceedingService;
    private final AwsS3Service awsS3Service;

    @Override
    @Transactional
    public UserJoinRes join(UserJoinReq userJoinReq) {
        if (!Pattern.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$", userJoinReq.getPassword())) throw new UserPasswordValidException();
        if (! userRepository.findByPhone(userJoinReq.getPhone()).isEmpty()) throw new UserDuplicationPhoneException();
        userJoinReq.setPassword(SHA256Util.encrypt(userJoinReq.getPassword()));

        if(userJoinReq.getPhoto() != null) userJoinReq.setPhotoURL(awsS3Service.uploadImage(userJoinReq.getPhoto()));

        User user = userRepository.save(UserJoinReq.toEntity(userJoinReq));

        questProceedingService.initAllQuest(CreateQuestProceedingReq.create(user));
        return UserJoinRes.builder().username(user.getNickName()).build();
    }

    @Override
    @Transactional
    public ApplicationResponse<Void> delete(String password) {


        User user = userRepository.findByLoginId(jwtService.getLoginId()).orElseThrow(NotFoundUserException::new);
        if(!user.getPassword().equals(SHA256Util.encrypt(password))) throw new UserPasswordWrongException();

        user.changeUserDelete();
        return ApplicationResponse.ok();

}

    @Override
    @Transactional
    public UserLoginRes login(UserLoginReq userLoginReq) {

        User user = userRepository.findByLoginId(userLoginReq.getLoginId()).orElseThrow(NotFoundUserException::new);

        if(!user.getPassword().equals(SHA256Util.encrypt(userLoginReq.getPassword())))throw new NotFoundUserException();
        if(user.getStatus() == PresenceStatus.DELETE) throw new UserDeleteException();

        String accessJwt = jwtService.createAccessJwt(user.getLoginId());
        String refreshJwt = jwtService.createRefreshJwt(user.getLoginId());
        long refreshTokenIdx = userRefreshTokenService.insertRefreshToken(refreshJwt, user);

        return UserLoginRes.from(user , accessJwt , refreshTokenIdx);
    }

    @Override
    public String checkNickname(String nickName) {
        if (userRepository.findByNickName(nickName).isEmpty()) {
            if (Pattern.matches("^[a-z0-9가-힣]{2,5}$", nickName)) {
                return null;
            }else {
                throw new UserNickNameValidException();
            }
        } else {
            throw new UserNickNameDuplicationException();
        }
    }

    @Override
    public String checkLoginId(String loginId) {
        if (userRepository.findByLoginId(loginId).isEmpty()) {
            if (Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$", loginId)) {
                return null;
            } else {
                throw new UserIDValidException();
            }
        } else {
            throw new UserIdDuplicationException();
        }
    }

    @Override
    public List<UserFindRes> findUser(UserFindReq userFindReq) {

        if (userFindReq.getPg() <=0 || userFindReq.getPg() > 15 || userFindReq.getSz() <= 0 || userFindReq.getUserNickName() == null || userFindReq.getUserNickName().isEmpty()) throw new ValidUserFindPagingException();

        List<User> users = userRepository.findUserName(userFindReq);

        if (users.isEmpty()) throw new NotFoundUserException();

        List<UserFindRes> result = new ArrayList<>();

        for (User user : users) result.add(UserFindRes.from(user));

        return result;
    }

    @Override
    @Transactional
    public UserUpdateFormRes getUpdateForm() {

        return UserUpdateFormRes.create(userRepository.findByLoginId(jwtService.getLoginId()).orElseThrow(NotFoundUserException::new));
    }

    @Override
    @Transactional
    public ApplicationResponse<Void> update(UserUpdateReq userUpdateReq) {
        User user = userRepository.findByLoginId(jwtService.getLoginId()).orElseThrow(NotFoundUserException::new);

        String photoUrl ="";

        if(userUpdateReq.getPhoto() != null) photoUrl = awsS3Service.uploadImage(userUpdateReq.getPhoto());

        user.updateUser(userUpdateReq , photoUrl);

        return ApplicationResponse.ok();

    }

    @Override
    public UserHomeRes home() {
        User user = userRepository.findByLoginId(jwtService.getLoginId()).get();

        return UserHomeRes.builder()
                .today(ChronoUnit.DAYS.between(user.getSignUpDate(), LocalDateTime.now()))
                .level(user.getLevel())
                .growth(user.getGrowth())
                .nickName(user.getNickName())
                .step(0)
                .time(0)
                .QuestPhoto(null)
                .todayQuest(null)
                .build();
    }

    @Override
    public UserInfoRes info() {
        User user = userRepository.findByLoginId(jwtService.getLoginId()).get();
        String grade;
        if (user.getLevel() < 10) {
            grade = "프린이";
        }else if (user.getLevel() < 20){
            grade = "프린이2";
        }else if (user.getLevel()<30){
            grade="프린이3";
        }else{
            grade="프린이4";
        }
        return UserInfoRes.builder()
                .nickname(user.getNickName())
                .grade(grade)
                .level(user.getLevel())
                .photo(AwsS3Service.makeUrlOfFilename(user.getPhoto()))
                .step(0L)
                .build();
    }

    @Override
    public String terms(String condition) {
        String value=null;
        if(condition.equals("privacy")){
            value = "1. 개인정보의 처리 목적플렙업은(는) 다음의 목적을 위하여 개인정보를 처리하고 있으며, 다음의 목적 이외의 용도로는 이용하지 않습니다.- 고객 가입의사 확인, 고객에 대한 서비스 제공에 따른 본신 식별 및 인증, 회원자격 유지 및 관리, 물품이나 서비스 공급에 따른 금액 결제, 물품 또는 서비스의 공급 및 배송 등" + "\n"+
                    "    2. 개인정보의 처리 및 보유 기간" + "\n"+
                    "1) 플렙업은(는) 정보 주체로 부터 개인정보를 수집할 때 동의 받은 개인정보 보유 이용기간 또는 법령에 따른 개인정보 보유, 이용기간 내에서 개인정보를 처리 및 보유 합니다." + "\n"+
                    "2) 구체적인 개인정보 보유 기간은 다음과 같습니다." + "\n"+
                    "- 고객가입 및 관리 : 서비스 이용 계약 또는 회원가입 해지시 까지" + "\n"+
                    "3. 개인정보의 제3자 제공에 관한 사항" + "\n"+
                    "1) 플렙업은(는) 정보주체의 동의, 법률의 특별한 규정등 개인정보 보호법 제 17조 및 18조에 해당하는 경우에만 개인정보를 제 3자에게 제공합니다." + "\n"+
                    "2) 플렙업은(는) 다음과 같이 개인정보를 제 3자에게 제공하고 있습니다." + "\n"+
                    "- 개인정보를 제공받는 자 : 구글 (Google)" + "\n"+
                    "- 제공받는 자의 개인정보 이용목적 : 다운받은 앱 사용시 수명주기와 발생 이벤트 등의 분석 및 통계용 (구글 애널리틱스), 정보주체별 맞춤광고 제공용(구글 애드몹)" + "\n"+
                    "- 제공받는 자의 보유, 이용기간 : 앱 설치시 부터 제품별 구글이 정한 기간에 따름" + "\n"+
                    "- 제공받는 자의 제품별 개인정보처리방침 : 구글 개인정보 처리방침에 따름 (https://policies.google.com/privacy?hl=ko)" + "\n"+
                    "4. 개인정보처리 위탁" + "\n"+
                    "① 플렙업은(는) 원활한 개인정보 업무처리를 위하여 다음과 같이 개인정보 처리업무를 위탁하고 있습니다." + "\n"+
                    "1. [위탁없음.]" + "\n"+
                    "- 위탁받는 자 (수탁자) : 없음." + "\n"+
                    "- 위탁하는 업무의 내용 : 없음." + "\n"+
                    "- 위탁기간 : 없음." + "\n"+
                    "② 플렙업은(는) 위탁계약 체결시 개인정보 보호법 제25조에 따라 위탁업무 수행목적 외 개인정보 처리금지, 기술적․관리적 보호조치, 재위탁 제한, 수탁자에 대한 관리․감독, 손해배상 등 책임에 관한 사항을 계약서 등 문서에 명시하고, 수탁자가 개인정보를 안전하게 처리하는지를 감독하고 있습니다." + "\n"+
                    "③ 위탁업무의 내용이나 수탁자가 변경될 경우에는 지체없이 본 개인정보 처리방침을 통하여 공개하도록 하겠습니다." + "\n"+
                    "5. 정보주체와 법정대리인의 권리·의무 및 그 행사방법 이용자는 개인정보주체로써 다음과 같은 권리를 행사할 수 있습니다." + "\n"+
                    "① 정보주체는 플렙업에 대해 언제든지 다음 각 호의 개인정보 보호 관련 권리를 행사할 수 있습니다." + "\n"+
                    "1. 개인정보 열람요구" + "\n"+
                    "2. 오류 등이 있을 경우 정정 요구" + "\n"+
                    "3. 삭제요구" + "\n"+
                    "4. 처리정지 요구" + "\n"+
                    "6. 처리하는 개인정보의 항목 작성 " + "\n"+
                    "① 플렙업은(는) 다음의 개인정보 항목을 처리하고 있습니다." + "\n"+
                    "- 필수항목 : 없음." + "\n"+
                    "- 선택항목 : 없음." + "\n"+
                    "7. 개인정보의 파기." + "\n"+
                    "플렙업은(는) 원칙적으로 개인정보 처리목적이 달성된 경우에는 지체없이 해당 개인정보를 파기합니다. 파기의 절차, 기한 및 방법은 다음과 같습니다." + "\n"+
                    "-파기절차." + "\n"+
                    "이용자가 입력한 정보는 목적 달성 후 별도의 DB에 옮겨져(종이의 경우 별도의 서류) 내부 방침 및 기타 관련 법령에 따라 일정기간 저장된 후 혹은 즉시 파기됩니다. 이 때, DB로 옮겨진 개인정보는 법률에 의한 경우가 아니고서는 다른 목적으로 이용되지 않습니다." + "\n"+
                    "-파기기한 이용자의 개인정보는 개인정보의 보유기간이 경과된 경우에는 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성, 해당 서비스의 폐지, 사업의 종료 등 그 개인정보가 불필요하게 되었을 때에는 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 5일 이내에 그 개인정보를 파기합니다." + "\n"+
                    "8. 개인정보 자동 수집 장치의 설치•운영 및 거부에 관한 사항." + "\n"+
                    "플렙업은(는) 정보주체의 이용정보를 저장하고 수시로 불러오는 쿠키를 사용하지 않습니다." + "\n"+
                    "정보 주체가 제3자인 구글에게 제공하는 정보는 플렙업와 상관없이 정보 주체 기기상의 구글 설정에 따릅니다." + "\n"+
                    "9. 개인정보 보호책임자 작성." + "\n"+
                    "① 플렙업은(는) 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다." + "\n"+
                    "▶ 개인정보 보호책임자 ." + "\n"+
                    "이새별 (이메일 : leesaebyeol528@gmail.com)." + "\n"+
                    "10. 개인정보 처리방침 변경." + "\n"+
                    "①이 개인정보처리방침은 시행일로부터 적용되며, 법령 및 방침에 따른 변경내용의 추가, 삭제 및 정정이 있는 경우에는 변경사항의 시행 7일 전부터 공지사항을 통하여 고지할 것입니다.";
        }else if(condition.equals("service")){
            value = "제1조(목적)" + "\n"+
                    "본 회원약관은 플렙업(이하 \"갑\"라 한다)이 운영하는 인터넷관련 서비스(이하 \"서비스\"라 한다)를 이용함에 있어 관리자와 이용자(이하 \"회원\"라 한다)의 권리, 의무 및 책임사항을 규정함을 목적으로 한다." + "\n"+
                    "제2조 (약관의 효력)" + "\n"+
                    "1.본 약관은 \"갑\"에 회원 가입 시 회원들에게 통지함으로써 효력을 발생합니다." + "\n"+
                    "2.\"갑\"은 이 약관의 내용을 변경할 수 있으며, 변경된 약관은 제1항과 같은 방법으로 공지 또는 통지함으로써 효력을 발생합니다." + "\n"+
                    "3.약관의 변경사항 및 내용은 \"갑\"의 홈페이지에 게시하는 방법으로 공시합니다." + "\n"+
                    "제3조 (약관 이외의 준칙)" + "\n"+
                    "이 약관에 명시되지 않은 사항이 전기 통신 기본법, 전기통신 사업법, 기타 관련 법령에 규정되어 있는 경우 그 규정에 따릅니다." + "\n"+
                    "제4조 (이용계약의 체결) 회원 가입 시 회원 약관 밑에 있는 동의버튼을 누르면 약관에 동의하여 이 계약이 체결된 것으로 간주한다." + "\n"+
                    "제5조 (용어의 정의)" + "\n"+
                    "이 약관에서 사용하는 용어의 정의는 다음과 같습니다." + "\n"+
                    "1.회원: \"갑\"과 서비스 이용에 관한 계약을 체결한 자" + "\n"+
                    "2.아이디(ID): 회원 식별과 회원의 서비스 이용을 위하여 회원이 선정하고 \"갑\"이 승인하는 문자와 숫자의 조합" + "\n"+
                    "3.비밀번호: 회원이 통신상의 자신의 비밀을 보호하기 위해 선정한 문자와 숫자의 조합" + "\n"+
                    "제6조 (이용신청)" + "\n"+
                    "1.회원 가입은 온라인으로 가입신청 양식에 기록하여 \"갑\"에 제출함으로써 이용신청을 할 수 있습니다." + "\n"+
                    "2.가입희망 회원은 반드시 자신의 본명 및 주민등록번호로 이용신청을 하여야 하며, 1개의 ID만 신청을 할 수 있습니다." + "\n"+
                    "제7조 (회원가입의 승낙)" + "\n"+
                    "\"갑\"의 회원 가입 신청 양식에 가입 희망 회원이 인터넷으로 제6조와 같이 신청하면 \"갑\"은 바로 가입을 승인하여 서비스를 이용할 수 있다." + "\n"+
                    "제8조(회원가입 신청거절 및 강제 탈퇴)" + "\n"+
                    "1. \"갑\"은 타인의 명의나 주민등록번호를 도용하여 회원가입신청을 할 경우 회원가입신청을 거절할 수 있다." + "\n"+
                    "2. 회원가입신청이 승인이 된 후에도 허위사실의 기재가 발각되거나 \"갑\"의 명예를 회손시키거나 음란물이나 불건전한 내용을 게재할 경우 회원의 자격을 강제 탈퇴시킬 수 있다." + "\n"+
                    "제9조 (서비스 제공의 중지)" + "\n"+
                    "\"갑\"은 다음 각 호의 1에 해당하는 경우 서비스의 제공을 중지할 수 있습니다" + "\n"+
                    "1.설비의 보수 등을 위하여 부득이한 경우" + "\n"+
                    "2.전기통신사업법에 규정된 기간통신사업자가 전기통신서비스를 중지하는 경우3.기타 \"갑\"이 서비스를 제공할 수 없는 사유가 발생한 경우.\\" + "\n"+
                    "제10조 (\"갑\"의 의무)" + "\n"+
                    "1.\"갑\"은 계속적, 안정적으로 서비스를 제공할 수 있도록 최선의 노력을 다하여야 합니다." + "\n"+
                    "2.\"갑\"은 항상 회원의 신용정보를 포함한 개인신상정보의 보안에 대하여 관리에 만전을 기함으로서 회원의 정보보안에 최선을 다하여야 합니다.\\" + "\n"+
                    "제11조 (개인정보보호)" + "\n"+
                    "1.\"갑\"은 이용자의 정보수집시 서비스의 제공에 필요한 최소한의 정보를 수집합니다." + "\n"+
                    "2.제공된 개인정보는 당해 이용자의 동의없이 목적외의 이용이나 제3자에게 제공할 수 없으며, 이에 대한 모든 책임은 \"갑\"이 집니다. 다만, 다음의 경우에는 예외로 합니다." + "\n"+
                    "①통계작성, 학술연구 또는 시장조사를 위하여 필요한 경우로서 특정 개인을 식별할 수 없는 형태로 제공하는 경우" + "\n"+
                    "②전기통신기본법 등 법률의 규정에 의해 국가기관의 요구가 있는 경우" + "\n"+
                    "③범죄에 대한 수사상의 목적이 있거나 정보통신윤리 위원회의 요청이 있는 경우④기타 관계법령에서 정한 절차에 따른 요청이 있는 경우" + "\n"+
                    "3.회원은 언제든지 \"갑\"이 가지고 있는 자신의 개인정보에 대해 열람 및 오류정정을 요구할 수 있으며 \"갑\"은 이에 대해 지체없이 처리합니다." + "\n"+
                    "제12조 (회원의 의무)" + "\n"+
                    "1.회원은 관계법령, 이 약관의 규정, 이용안내 및 주의사항 등 \"갑\"이 통지하는 사항을 준수하여야 하며, 기타 \"갑\"의 업무에 방해되는 행위를 하여서는 안됩니다." + "\n"+
                    "2.회원은 \"갑\"의 사전 승낙 없이 서비스를 이용하여 어떠한 영리 행위도 할 수 없습니다." + "\n"+
                    "3.회원은 서비스를 이용하여 얻은 정보를 \"갑\"의 사전 승낙 없이 복사, 복제, 변경, 번역, 출판,방송 기타의 방법으로 사용하거나 이를 타인에게 제공할 수 없습니다." + "\n"+
                    "4.회원은 이용신청서의 기재내용 중 변경된 내용이 있는 경우 서비스를 통하여 그내용을 \"갑\"에게 통지하여야 합니다." + "\n"+
                    "5.회원은 서비스 이용과 관련하여 다음 각 호의 행위를 하여서는 안됩니다." + "\n"+
                    "①다른 회원의 아이디(ID)를 부정 사용하는 행위" + "\n"+
                    "②범죄행위를 목적으로 하거나 기타 범죄행위와 관련된 행위" + "\n"+
                    "③선량한 풍속, 기타 사회질서를 해하는 행위" + "\n"+
                    "④타인의 명예를 훼손하거나 모욕하는 행위" + "\n"+
                    "⑤타인의 지적재산권 등의 권리를 침해하는 행위" + "\n"+
                    "⑥해킹행위 또는 컴퓨터바이러스의 유포행위" + "\n"+
                    "⑦타인의 의사에 반하여 광고성 정보 등 일정한 내용을 지속적으로 전송 또는 타 사이트를 링크하는 행위" + "\n"+
                    "⑧서비스의 안전적인 운영에 지장을 주거나 줄 우려가 있는 일체의 행위" + "\n"+
                    "⑨기타 관계법령에 위배되는 행위" + "\n"+
                    "⑩게시판 등 커뮤니티를 통한 상업적 광고홍보 또는 상거래 행위" + "\n"+
                    "제13조 (게시물 또는 내용물의 삭제)" + "\n"+
                    "\"갑\"은 서비스의 게시물 또는 내용물이 제12조의 규정에 위반되거나 \"갑\" 소정의 게시기간을 초과하는 경우 사전 통지나 동의 없이 이를 삭제할 수 있습니다." + "\n"+
                    "제14조 (게시물에 대한 권리·의무)" + "\n"+
                    "게시물에 대한 저작권을 포함한 모든 권리 및 책임은 이를 게시한 회원에게 있습니다." + "\n"+
                    "제15조 (양도금지) 회원이 서비스의 이용권한, 기타 이용계약상 지위를 타인에게 양도, 증여할 수 없으며, 이를 담보로 제공할 수 없습니다." + "\n"+
                    "제16조 (계약해지 및 이용제한)" + "\n"+
                    "1.회원이 이용계약을 해지하고자 하는 때에는 본인이 서비스 또는 전자 우편을 통하여 해지하고자 하는 날의 1일전까지 (단, 해지일이 법정 공휴일인 경우 공휴일개시 2일전까지)이를 \"갑\"에게 신청하여야 합니다." + "\n"+
                    "2.\"갑\"은 해지 및 탈퇴를 한 회원이 다시 이용신청을 하는 경우 일정기간 그 승낙을 제한할 수 있습니다." + "\n"+
                    "제17조 (면책·배상)" + "\n"+
                    "1.\"갑\"은 회원이 서비스에 게재한 정보, 자료, 사실의 정확성, 신뢰성 등 그 내용에 관하여는 어떠한 책임을 부담하지 아니하고, 회원은 자기의 책임아래 서비스를 이용하며, 서비스를 이용하여 게시 또는 전송한 자료 등에 관하여 손해가 발생하거나 자료의 취사 선택, 기타서비스 이용과 관련하여 어떠한 불이익이 발생하더라도 이에 대한 모든 책임은 회원에게 있습니다." + "\n"+
                    "2.\"갑\"은 제12조의 규정에 위반하여 회원간 또는 회원과 제3자간에 서비스를 매개로 하여 물품거래 등과 관련하여 어떠한 책임도 부담하지 아니하고, 회원이 서비스의 이용과 관련하여 기대하는 이익에 관하여 책임을 부담하지 않습니다." + "\n"+
                    "3.회원 아이디(ID)와 비밀번호의 관리 및 이용상의 부주의로 인하여 발생 되는 손해 또는 제3자에 의한 부정사용 등에 대한 책임은 모두 회원에게 있습니다." + "\n"+
                    "4.회원이 제12조, 기타 이 약관의 규정을 위반함으로 인하여 \"갑\"이 회원 또는 제3자에 대하여 책임을 부담하게 되고, 이로써 \"갑\"에게 손해가 발생하게 되는 경우, 이 약관을 위반한 회원은 \"갑\"에게 발생하는 모든 손해를 배상하여야 하며, 동 손해로부터 \"갑\"을 면책시켜야 합니다." + "\n"+
                    "제18조 (분쟁의 해결)" + "\n" +
                    "1.\"갑\"과 회원은 서비스와 관련하여 발생한 분쟁을 원만하게 해결하기 위하여 필요한 모든 노력을 하여야 합니다." + "\n" +
                    "2. 1항의 규정에도 불구하고 분쟁으로 인하여 소송이 제기될 경우 소송은 \"갑\"의 소재지를 관할하는 법원의 관할로 합니다." + "\n" +
                    "부 칙 제 1 조 (시행일) 이 약관은 2016년 06월 01일부터 시행한다.";
        } else {
            throw new UserTermsException();
        }
        return value;
    }

    @Override
    public String findId(String phoneNum) {
        User user = userRepository.findByPhone(phoneNum).orElseThrow(NotFoundUserException::new);
        String loginId = user.getLoginId();
        return loginId.substring(0, loginId.length() - 2) + "**";
    }

    @Override
    @Transactional
    public Boolean findPw(String loginId, String password) {
        User user = userRepository.findByLoginId(loginId).orElseThrow(NotFoundUserException::new);
        if (Pattern.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$", password)) {
            user.changePw(SHA256Util.encrypt(password));
            return true;
        }else {
            throw new UserPasswordValidException();
        }
    }

    @Override
    public Boolean checkPhone(String loginId, String phone) {
        userRepository.findByLoginIdAndPhone(loginId, phone).orElseThrow(NotFoundUserException::new);
        return true;
    }
}