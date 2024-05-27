package com.example.landing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.landing.service.ApplyService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ApplyController {

    private final ApplyService service;

    @GetMapping("/{country}/{pagePath}")
    public String landing(
            HttpServletRequest request,
            Model model,
            HttpSession sesion,
            @PathVariable(value = "pagePath") String pagePath,
            @PathVariable(value = "country") String country,
            @RequestParam(value = "cid") Long cid) {
        return service.conexion(request, model, sesion, pagePath,country,cid);
    }

    @GetMapping("/{pagePath}/msisdn")
    public String msisdn(
            HttpServletRequest request,
            Model model,
            HttpSession sesion,
            @PathVariable(value = "pagePath") String pagePath,
            @RequestParam(value = "msisdn", required = true, defaultValue = "") Long msisdn,
            @RequestParam(value = "spId", required = true, defaultValue = "") Long spId,
            @RequestParam(value = "shortcode", required = true, defaultValue = "") String shortCode) {

        return service.msisdn(request, model, sesion, pagePath,msisdn,spId,shortCode);
    }

    @PostMapping("/{pagePath}/deteccion")
    public String deteccion(
            Model model,
            HttpSession sesion,
            @PathVariable(value = "pagePath") String pagePath,
            @RequestParam(value = "msisdn", required = true, defaultValue = "") String msisdn,
            @RequestParam(value = "spId", required = true, defaultValue = "") Long spId,
            @RequestParam(value = "shortcode", required = true, defaultValue = "") String shortCode

    ) {

        return service.deteccion(model, sesion, pagePath,msisdn,spId,shortCode);
    }

    @PostMapping("/{pagePath}/comprobarPin")
    public String pin(
            HttpServletRequest request,
            Model model,
            HttpSession sesion,
            @PathVariable(value = "pagePath") String pagePath,
            @RequestParam(value = "pin") Integer pin) {

        return service.pin(request, model, sesion, pagePath,pin);
    }

}
