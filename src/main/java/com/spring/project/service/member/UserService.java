package com.spring.project.service.member;

import com.spring.project.entity.Cart;
import com.spring.project.entity.User;
import com.spring.project.repository.member.UserRepository;
import com.spring.project.request.JoinDto;
import com.spring.project.request.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public void join(JoinDto req){
        validateDuplicationUser(req.toEntity(encoder.encode(req.getPassword())));
        userRepository.save(req.toEntity(encoder.encode(req.getPassword())));
    }

    private void validateDuplicationUser(User user){
        userRepository.findByEmail(user.getEmail())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public boolean checkDuplicatedUserName(String name) {
        return userRepository.existsByName(name);
    }

    public User login(LoginDto req) {
        Optional<User> member= userRepository.findByEmail(req.getEmail());

        if(member.isEmpty()) { // 해당 이메일을 가진 사용자가 없는 경우
            return null;
        }
        User findUser =member.get();

        if(!findUser.getPassword().equals(req.getPassword())) return null;

        return findUser;
    }

    public List<User> findUserList(){
        return userRepository.findAll();
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElse(null);
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElse(null);
    }

    public Cart getUserCart(User user) {
        return user.getCart();
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
