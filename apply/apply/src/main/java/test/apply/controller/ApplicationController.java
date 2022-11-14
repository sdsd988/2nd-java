package test.apply.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import test.apply.domain.Application;
import test.apply.repository.ApplicationRepository;
import test.apply.service.ApplicationService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationRepository applicationRepository;

    /**
     * 지원서 생성
     */
    @PostMapping("/application/new")
    public String createApplication(@RequestParam("memberId") Long memberId,
                                    @RequestParam("lectureId") Long lectureId) {

        applicationService.createApplication(memberId, lectureId);

        return "redirect:/applications";
    }

    /**
     * 지원서 전체 조회
     */
    @GetMapping("/applications")
    public String getAllApplication(Model model) {
        List<Application> allApplications = applicationService.findAllApplication();
        model.addAttribute("allApplications", allApplications);
        return "applications/applicationsList";
    }

    /**
     * 지원서 삭제
     */

    @DeleteMapping("/application/{applicationId}")
    public void deleteApplication(@PathVariable Long applicationId) {
        Application findApplication = applicationRepository.findOne(applicationId);
        applicationService.deleteApplication(findApplication);
    }

    /**
     * 지원서 수정
     */

    @PutMapping("/application/{applicationId}")
    public void putApplication(@PathVariable Long applicationId, @RequestBody Application application){
        applicationService.updateApplication(applicationId,application);
    }
}
