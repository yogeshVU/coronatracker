package com.goalabs.coronavirustracker.controllers;

import com.goalabs.coronavirustracker.models.LocationStats;
import com.goalabs.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("locationStats", coronaVirusDataService.getAllStats());
        List<LocationStats> allstats = coronaVirusDataService.getAllStats();
        int tolalcases = allstats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();

        int totalnewcases = allstats.stream().mapToInt(LocationStats::getDiffFromPreviousDay).sum();
        model.addAttribute("totalReportedCases",tolalcases);
        model.addAttribute("totalNewCases",totalnewcases);
        return "home";
    }

}
