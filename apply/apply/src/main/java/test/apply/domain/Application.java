package test.apply.domain;

import lombok.Getter;
import lombok.Setter;
import test.apply.Exception.AfterSubmitException;
import test.apply.Exception.AlreadySubmitException;
import test.apply.Exception.BeforeSubmitException;
import test.apply.domain.lecture.Lecture;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
public class Application {

    @Id
    @GeneratedValue
    @Column(name = "application_id")
    private Long id;

    private String telNumber;

    private String motivation;

    private String MemberName;

    private String futureCourse;

    private ApplicationStatus status;

    private LocalDate applyDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //== 지원서 작성 메서드 ==//
    public static Application createApplication(Member member, Lecture lecture) {
        Application application = new Application();
        application.setMember(member);
        application.setStatus(ApplicationStatus.APPLY_ING);
        application.setLecture(lecture);
        application.setApplyDate(LocalDate.now());
        member.removeApplicationCount();

        return application;
    }

    //==지원서 제출 메서드==//

    public static Application submitApplication(Member member, Application application) {

        List<Application> applications = member.getApplications();

        //지원서 최종 제출 여부 확인
        for (Application findApplication : applications) {
            if (findApplication.getLecture() == application.getLecture() &&
                    findApplication.getStatus() == ApplicationStatus.APPLY_COMPLETE) {
                throw new AlreadySubmitException("이미 최종 지원 완료된 강의입니다.");
            }
        }

        //제출 기간 준수 여부 확인
        int result = application.getApplyDate().compareTo(application.getLecture().getLectureStart());
        if(result == -1){
            throw new BeforeSubmitException("강의 신청 기간이 시작되지 않았습니다.");
        } else if (result==1) {
            throw new AfterSubmitException("강의 신청 기간이 지났습니다.");
        }

        application.setStatus(ApplicationStatus.APPLY_COMPLETE);
        return application;
    }

    /**
     * 지원서 작성 기간 만료
     */
    public void expireApplicationDate(Application application, Lecture lecture){


        getMember().addMaxApplication(); // 지원서 갯수 복구
    }


    // == 연관관계 메서드 ==//
    public void setMember(Member member) {
        this.member = member;
        member.getApplications().add(this);
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
        lecture.getApplications().add(this);
    }


}
