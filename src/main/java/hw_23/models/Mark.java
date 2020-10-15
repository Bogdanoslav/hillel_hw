package hw_23.models;

import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "Lesson")
    private int LessonId;

    @ManyToOne (optional=false, cascade=CascadeType.PERSIST)
    @JoinColumn (name="StudentId")
    private Student student;

    @Column(name = "Mark")
    private int Mark;

    public Mark(int lessonId, int mark,Student student) {
        LessonId = lessonId;
        this.student = student;
        Mark = mark;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "ID=" + ID +
                ", LessonId=" + LessonId +
                ", Mark=" + Mark +
                '}';
    }
}
