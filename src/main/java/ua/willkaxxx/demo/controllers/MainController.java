//package ua.willkaxxx.demo.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import ua.willkaxxx.demo.entity.Role;
//import ua.willkaxxx.demo.repository.ExhibitionRepository;
//import ua.willkaxxx.demo.repository.UserRepository;
//
//@Controller
//public class MainController {
//
//    private final UserRepository userRepository;
//    private final ExhibitionRepository exhibitionRepository;
//
//    public MainController(UserRepository userRepository, ExhibitionRepository exhibitionRepository) {
//        this.userRepository = userRepository;
//        this.exhibitionRepository = exhibitionRepository;
//    }
//
//    @GetMapping
//    public String home() {
//        return "home";
//    }
//
////    @GetMapping("/masters")
////    public String masters(Model model) {
////        model.addAttribute("masters", userRepository.findAllByRole(Role.MASTER.ordinal()));
////        return "guest/masters";
////    }
//
//    @GetMapping("/services")
//    public String services(Model model) {
//        model.addAttribute("services", exhibitionRepository.findAll());
//        return "guest/services";
//    }
//
////    @GetMapping("/masterServices")
////    public String masterServices(@RequestParam(name = "id") Long id,
////                              @RequestParam(name = "mName") String mName,
////                              Model model) {
////        model.addAttribute("services", exhibitionRepository.getServiceByUserId(id));
////        model.addAttribute("mName",mName);
////        return "guest/masterServices";
////    }
//
//    @GetMapping("/serviceMasters")
//    public String serviceMasters(@RequestParam(name = "id") Long id,
//                                 @RequestParam(name = "sName") String sName,
//                                 Model model) {
//        model.addAttribute("masters", userRepository.getMastersByServiceId(id));
//        model.addAttribute("sName", sName);
//        return "guest/serviceMasters";
//    }
//}
