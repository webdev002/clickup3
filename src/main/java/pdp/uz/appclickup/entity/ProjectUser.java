package pdp.uz.appclickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pdp.uz.appclickup.entity.enums.TaskPermission;
import pdp.uz.appclickup.entity.template.AbstractIntegerEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProjectUser extends AbstractIntegerEntity {

    @OneToOne(fetch = FetchType.LAZY)
    private Project project;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private TaskPermission taskPermission;
}
