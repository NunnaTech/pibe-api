package mx.com.pandadevs.pibeapi.models.vacants.service;

import mx.com.pandadevs.pibeapi.models.auth.EmailService;
import mx.com.pandadevs.pibeapi.models.processes.Process;
import mx.com.pandadevs.pibeapi.models.processes.ProcessRepository;
import mx.com.pandadevs.pibeapi.models.processes.dto.ProcessDto;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import mx.com.pandadevs.pibeapi.models.vacants.dto.UserVacantDto;
import mx.com.pandadevs.pibeapi.models.vacants.dto.VacantProcessDto;
import mx.com.pandadevs.pibeapi.models.vacants.entities.UserVacant;
import mx.com.pandadevs.pibeapi.models.vacants.entities.Vacant;
import mx.com.pandadevs.pibeapi.models.vacants.mapper.UserVacantMapper;
import mx.com.pandadevs.pibeapi.models.vacants.repository.UserVacantRepository;
import mx.com.pandadevs.pibeapi.models.vacants.repository.VacantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserVacantService {

    private Logger logger = LoggerFactory.getLogger(UserVacantService.class);

    private final UserVacantMapper mapper;


    public UserVacantService(UserVacantMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserVacantRepository userVacantRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VacantRepository vacantRepository;
    @Autowired
    private ProcessRepository processRepository;

    private Optional<UserVacant> findByVacantAndUser(Long idUser, Integer idVacant) {
        return userVacantRepository.findByUser_IdAndVacant_Id(idUser, idVacant);
    }

    @Transactional(readOnly = true)
    public List<UserVacantDto> getVacantsByUser(String username) {
        try {
            return mapper.toUserVacantsDto(userVacantRepository.findAllByUser_Username(username));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Transactional(readOnly = true)
    public List<VacantProcessDto> getUsersByVacant(Integer id) {
        return mapper.toVacantsProcessDto(userVacantRepository.findAllByVacant_Id(id));
    }

    @Transactional
    public Boolean applyToVacant(Integer id, String username) {
        User user = userRepository.findByUsername(username);
        Optional<Vacant> vacant = vacantRepository.findByIdAndActiveIsTrue(id);
        Optional<Process> process = processRepository.findByIdAndActiveIsTrue(1);
        Optional<UserVacant> userVacant = findByVacantAndUser(user.getId(), id);
        if (user != null && vacant.isPresent() && process.isPresent() && !userVacant.isPresent()) {
            emailService.sendEmailNewVacant(user, vacant.get());
            userVacantRepository.save(new UserVacant(user, vacant.get(), process.get()));
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean processToVacant(Integer id, ProcessDto processDto) {
        Optional<UserVacant> userVacant = userVacantRepository.findById(id);
        if (userVacant.isPresent()) {
            userVacant.get().setProcess(processRepository.findByIdAndActiveIsTrue(processDto.getId()).get());
            emailService.sendEmailCurrentlyProccess(userVacant.get(), userVacant.get().getProcess().getName().equals("Finalizado"));
            userVacantRepository.save(userVacant.get());
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean deleteUserToVacant(Integer id) {
        Optional<UserVacant> userVacant = userVacantRepository.findById(id);
        if (userVacant.isPresent()) {
            userVacantRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
