package test.apply.domain;


import lombok.Getter;
import lombok.Setter;
import test.apply.Exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue

    @Column(name = "member_id")
    private String email;

    private String password;

    private String member_name;

    private String telNumber;

    private Integer maxApplication = 5;

    @OneToMany(mappedBy = "member")
    private List<Application> applications = new ArrayList<>();
    // == 로직 ==//


    /**
     * 최대 지원서 갯수 차감
     */
    public void removeApplicationCount() {
        Integer now_application = this.maxApplication - 1;
        if (now_application < 1) {
            throw new NotEnoughStockException("작성 가능한 지원서 수를 초과하였습니다.");
        }

        this.maxApplication = now_application;
    }

    /**
     * 최대 지원서 갯수 증가
     */
    public void addMaxApplication(){
        this.maxApplication += 1;
    }
}
