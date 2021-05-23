package ua.willkaxxx.demo.controllers;

import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.willkaxxx.demo.entity.Exhibition;
import ua.willkaxxx.demo.entity.Hall;
import ua.willkaxxx.demo.repository.ExhibitionRepository;
import ua.willkaxxx.demo.repository.HallRepository;
import ua.willkaxxx.demo.repository.UserRepository;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/adm")
public class AdminController {
    private final ExhibitionRepository exhibitionRepository;
    private final UserRepository userRepository;
    private final HallRepository hallRepository;

    public AdminController(ExhibitionRepository exhibitionRepository, UserRepository userRepository, HallRepository hallRepository) {
        this.exhibitionRepository = exhibitionRepository;
        this.userRepository = userRepository;
        this.hallRepository = hallRepository;
    }


    @GetMapping("/home")
    public String admHome() {
        return "/admin/admHome";
    }

    @GetMapping("/manage/halls")
    public String manageHalls(Model model, @RequestParam(name = "page") Optional<Integer> curPage) {
        Pageable pageable = PageRequest.of(curPage.orElse(1) - 1, 5);
        Page<Hall> halls = hallRepository.findAll(pageable);
        model.addAttribute("page", halls);
        model.addAttribute("list", halls.getContent());
        model.addAttribute("currentPage", curPage.orElse(1));
        model.addAttribute("totalPages", halls.getTotalPages());
        return "/admin/manageHalls";
    }

    @GetMapping("/manage/halls/edit")
    public String createHall(Model model, @RequestParam(name = "id") Long id) {
        Hall hall = hallRepository.findById(id).orElse(new Hall());
        model.addAttribute("hall", hall);
        return "/admin/editHall";
    }

    @PostMapping("/manage/halls/edit")
    public String postManageHalls(Hall hall) {
        hallRepository.save(hall);
        return "redirect:/adm/manage/halls";
    }

    @GetMapping("/manage/exhibitions")
    public String manageExhibitions(Model model, @RequestParam(name = "page") Optional<Integer> curPage) {
        Pageable pageable = PageRequest.of(curPage.orElse(1) - 1, 2);
        Page<Exhibition> exhibitions = exhibitionRepository.findAll(pageable);
        model.addAttribute("page", exhibitions);
        model.addAttribute("list", exhibitions.getContent());
        model.addAttribute("currentPage", curPage.orElse(1));
        model.addAttribute("totalPages", exhibitions.getTotalPages());
        return "/admin/manageExhibitions";
    }

    @GetMapping("/manage/exhibitions/edit")
    public String createExhibition(Model model, @RequestParam(name = "id") Long id, @RequestParam(name = "hallPage") Optional<Integer> curHallPage) {
        Exhibition exhibition = exhibitionRepository.findById(id).orElse(new Exhibition());
        model.addAttribute("exhibition", exhibition);
        if (id > 0) {
            Pageable pageable = PageRequest.of(curHallPage.orElse(1) - 1, 5);
            Page<Hall> halls = hallRepository.findAllByExhibitions(exhibition, pageable);
            model.addAttribute("page", halls);
            model.addAttribute("list", halls.getContent());
            model.addAttribute("currentPage", curHallPage.orElse(1));
        } else
            model.addAttribute("list", new ArrayList<Hall>());
        return "/admin/editExhibition";
    }

    @GetMapping("/manage/exhibitions/edit/deleteHall")
    public String deleteExhibitionHall(@RequestParam(name = "hallId") Long hallId, @RequestParam(name = "exhibitionId") Long exhibitionId) {
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElse(new Exhibition());
        exhibition.setHalls(exhibition.getHalls().stream().filter(a -> !a.getId().equals(hallId)).collect(Collectors.toList()));
        System.out.println(exhibition.getHalls().size());
        exhibitionRepository.save(exhibition);
        return "redirect:/adm/manage/exhibitions/edit?id=" + exhibitionId;
    }

    @PostMapping("/manage/exhibitions/edit")
    public String postManageExhibition(Exhibition exhibition) {
        if (exhibition.getHttpHallsID() != null)
            exhibition.setHalls(exhibition.getHttpHallsID().stream().map(Hall::new).collect(Collectors.toList()));
        exhibitionRepository.save(exhibition);
        return "redirect:/adm/manage/exhibitions";
    }

    @PostMapping("/manage/exhibitions/delete")
    public String postDeleteExhibition(Exhibition exhibition) {
        exhibitionRepository.delete(exhibition);
        return "redirect:/adm/manage/exhibitions";
    }

//    @GetMapping("/services")
//    public String servicesAdm(Model model){
//        model.addAttribute("services" , exhibitionRepository.findAll());
//        return "/admin/serviceAdm";
//    }
//
//    @PostMapping("/services/edit")
//    public String editService(@RequestParam(name = "sid") Long sid,
//                              Model model) throws NoSuchFieldException {
//        model.addAttribute("service", exhibitionRepository.findById(sid).
//                orElseThrow(() -> new NoSuchFieldException("Service with id" + sid + "is not available")));
//        return "admin/serviceCreation";
//    }
//
//    @PostMapping("/services/delete")
//    public String deleteService(@RequestParam(name = "sid") Long sid) {
//        exhibitionRepository.deleteById(sid);
//        return "redirect:/admin/services";
//    }
//
//    @PostMapping("/services/create")
//    public String createService(Model model){
//        model.addAttribute("service", new Exhibition());
//        return "admin/serviceCreation";
//    }
//
//    @PostMapping("/services/save")
//    public String saveService(Exhibition exhibition){
//        exhibitionRepository.save(exhibition);
//        return "redirect:/admin/services";
//    }
//
//    @GetMapping("/masters")
//    public String masters(Model model){
////        model.addAttribute("masters", userRepository.findAllByRole(Role.MASTER.ordinal()));
//        return "admin/masterServices";
//    }
//
//    @PostMapping("/masters")
//    public String masterServices(@RequestParam(name = "mId") Long mId,
//                                 Model model) throws NoSuchFieldException {
//        model.addAttribute("master", userRepository.findById(mId)
//                .orElseThrow(() -> new NoSuchFieldException("Master with id" + mId + "is not available")));
////        model.addAttribute("masterServices", exhibitionRepository.getServiceByUserId(mId));
//        model.addAttribute("allServices", exhibitionRepository.findAll());
//        return "admin/editMasterServices";
//    }
}
