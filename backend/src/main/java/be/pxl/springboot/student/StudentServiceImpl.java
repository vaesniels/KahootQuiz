package be.pxl.springboot.student;

import be.pxl.springboot.security.Role;
import be.pxl.springboot.security.RoleRepository;
import be.pxl.springboot.security.User;
import be.pxl.springboot.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Student getById(long Id) {
        return studentRepository.findById(Id).get();
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public void addOrUpdate(Student student) {
        String username = student.getUsername();
        String password = student.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);

        Role role = new Role(username, "ROLE_STUDENT");
        User user = new User(username, encryptedPassword, true);

        userRepository.save(user);
        roleRepository.save(role);
        studentRepository.save(student);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
