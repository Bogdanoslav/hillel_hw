package hw_23.services;

import hw_21.Class;
import hw_23.dao.ClasssDao;
import hw_23.dao.MarkDao;
import hw_23.models.Classs;
import hw_23.models.Mark;

import java.util.List;

public class ClasssService {
    private ClasssDao classsDao = new ClasssDao();

    public ClasssService() {
    }

    public void saveClasss(Classs classs) {
        classsDao.save(classs);
    }

    public void deleteClasss(Classs classs) {
        classsDao.delete(classs);
    }

    public void updateClasss(Classs classs) {
        classsDao.update(classs);
    }

    public List<Classs> findAllClassses() {
        return classsDao.findAll();
    }
}
