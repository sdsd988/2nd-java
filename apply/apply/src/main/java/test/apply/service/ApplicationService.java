package test.apply.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.apply.Exception.AlreadySubmitException;
import test.apply.domain.Application;
import test.apply.domain.ApplicationStatus;
import test.apply.domain.Member;
import test.apply.domain.lecture.Lecture;
import test.apply.repository.ApplicationRepository;
import test.apply.repository.LectureRepository;
import test.apply.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final MemberRepository memberRepository;
    private final LectureRepository lectureRepository;
    private final ApplicationRepository applicationRepository;

    /**
     * 지원서 생성
     */

    @Transactional
    public void createApplication(Long memberId, Long lectureId) {

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Lecture lecture = lectureRepository.findOne(lectureId);

        // 지원서 생성
        Application application = Application.createApplication(member, lecture);

        //지원서 저장
        applicationRepository.save(application);

    }

    /**
     * 지원서 수정
     */

    @Transactional
    public void updateApplication(Long applicationId, Application application) {
        Application findApplication = applicationRepository.findOne(applicationId);

        if (findApplication.getStatus() == ApplicationStatus.APPLY_COMPLETE) {
            throw new AlreadySubmitException("제출 완료된 지원서는 수정이 불가합니다.");
        }
        findApplication.setMotivation(application.getMotivation());
        findApplication.setFutureCourse(application.getFutureCourse());
        findApplication.setApplyDate(application.getApplyDate());

    }

    /**
     * 지원서 전체 조회
     */

    public List<Application> findAllApplication() {
        return applicationRepository.findAllApplication();
    }

    /**
     * 지원서 삭제
     */

    @Transactional
    public void deleteApplication(Application application) {
        Application findApplication = applicationRepository.findOne(application.getId());

        if (findApplication.getStatus() == ApplicationStatus.APPLY_COMPLETE) {
            throw new AlreadySubmitException("제출 완료된 지원서는 삭제가 불가합니다.");
        }
        applicationRepository.deleteApplication(application);
    }
}
