package hw_23.services;

import hw_23.dao.MarkDao;
import hw_23.dao.StudentDao;
import hw_23.models.Mark;
import hw_23.models.Student;

import java.util.List;

public class MarkService {
    private MarkDao markDao = new MarkDao();

    public MarkService() {
    }

    public void saveMark(Mark mark) {
        markDao.save(mark);
    }

    public void deleteMark(Mark mark) {
        markDao.delete(mark);
    }

    public void updateMark(Mark mark) {
        markDao.update(mark);
    }

    public List<Mark> findAllMarks() {
        return markDao.findAll();
    }
}
