package mx.com.pandadevs.pibeapi.utils;

import mx.com.pandadevs.pibeapi.models.benefits.BenefitService;
import mx.com.pandadevs.pibeapi.models.logs.services.TableService;
import mx.com.pandadevs.pibeapi.models.modes.ModeService;
import mx.com.pandadevs.pibeapi.models.periods.PeriodService;
import mx.com.pandadevs.pibeapi.models.processes.ProcessService;
import mx.com.pandadevs.pibeapi.models.schedule.ScheduleService;
import mx.com.pandadevs.pibeapi.models.states.RepublicStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialDataBase implements CommandLineRunner {

    @Autowired
    private ModeService modeService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private PeriodService periodService;
    @Autowired
    private BenefitService benefitService;
    @Autowired
    private RepublicStateService republicStateService;
    @Autowired private ProcessService processService;
    @Autowired private TableService tableService;

    @Override
    public void run(String... args) throws Exception {
        modeService.fillInitialData();
        scheduleService.fillInitialData();
        periodService.fillInitialData();
        benefitService.fillInitialData();
        republicStateService.fillInitialData();
        processService.fillInitialData();
        tableService.fillInitialData();
    }
}
