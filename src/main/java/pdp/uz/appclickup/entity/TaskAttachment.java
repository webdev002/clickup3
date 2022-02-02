package pdp.uz.appclickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pdp.uz.appclickup.entity.template.AbstractIntegerEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskAttachment extends AbstractIntegerEntity {

    @OneToOne(fetch = FetchType.LAZY)
    private Task task;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment attachment;

    @Column(nullable = false)
    private boolean pinCoverImage;
}
