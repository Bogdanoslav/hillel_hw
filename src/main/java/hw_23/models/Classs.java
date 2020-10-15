package hw_23.models;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "classes")
public class Classs {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int ID;

    @OneToMany (mappedBy="classs", fetch=FetchType.EAGER, orphanRemoval = true)
    private Collection<Student> students;

    @Column(name = "Name")
    private String Name;

    public Classs(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Classs{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                '}';
    }
}
