//package ua.willkaxxx.demo.controllers;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import ua.willkaxxx.demo.entity.Exhibition;
//import ua.willkaxxx.demo.entity.User;
//import ua.willkaxxx.demo.repository.ExhibitionRepository;
//import ua.willkaxxx.demo.repository.UserRepository;
//
//@Controller
//@RequestMapping("/demo")
//public class DemoController {
//    private final UserRepository userRepository;
//    private final ExhibitionRepository exhibitionRepository;
//
//    public DemoController(UserRepository userRepository, ExhibitionRepository exhibitionRepository, ExhibitionRepository exhibitionRepository1) {
//        this.userRepository = userRepository;
//        this.exhibitionRepository = exhibitionRepository1;
//    }
//
//    @GetMapping(path = "/add")
//    public @ResponseBody
//    String addNewUser(@RequestParam String email,
//                      @RequestParam String password,
//                      @RequestParam(defaultValue = "2") int roleId) {
//
//        userRepository.save(User.builder()
//                .email(email)
//                .password(new BCryptPasswordEncoder().encode(password))
//                .role(roleId)
//                .build());
//        return "greeting";
//    }
//
//    @GetMapping(path = "/all")
//    public @ResponseBody
//    Iterable<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @GetMapping(path = "/allm")
//    public @ResponseBody
//    Iterable<User> getAllMasters() {
//        return userRepository.findAllByRole(2);
//    }
//
//}
//
