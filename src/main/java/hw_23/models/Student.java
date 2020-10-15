package hw_23.models;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @ManyToOne (cascade=CascadeType.PERSIST)
    @JoinColumn (name="GroupId")
    private Classs classs;

    @Column(name = "Name")
    private String name;

    @Column(name = "AdmissionYear")
    private int AdmissionYear;

    @OneToMany (mappedBy="student", fetch=FetchType.EAGER, orphanRemoval = true)
    private Collection<Mark> marks;

    public Student(String name, int admissionYear, Classs classs) {
        this.classs = classs;
        this.name = name;
        AdmissionYear = admissionYear;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", classs=" + classs +
                ", name='" + name + '\'' +
                ", AdmissionYear=" + AdmissionYear +
                ", marks=" + marks +
                '}';
    }
}
