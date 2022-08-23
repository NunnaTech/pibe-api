package mx.com.pandadevs.pibeapi.models.vacants.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import mx.com.pandadevs.pibeapi.models.vacants.controller.UserVacantFavoriteController;
import mx.com.pandadevs.pibeapi.models.vacants.dto.VacantDto;
import mx.com.pandadevs.pibeapi.models.vacants.entities.Vacant;
import mx.com.pandadevs.pibeapi.models.vacants.mapper.VacantMapper;
import mx.com.pandadevs.pibeapi.models.vacants.repository.VacantRepository;
import mx.com.pandadevs.pibeapi.security.LogJwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserVacantFavoriteService {
    private Logger logger = LoggerFactory.getLogger(UserVacantFavoriteService.class);

    @Autowired
    private VacantRepository vacantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogJwtService logJwtService;

    private final VacantMapper mapper;

    public UserVacantFavoriteService(VacantMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<VacantDto> getAll(String username, String bearerToken) throws JsonProcessingException {
        String authUsername = logJwtService.getOnlyUsername(bearerToken);
        if (authUsername.equals(username)) {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return mapper.toVacantsDto(user.getFavoitesVacants());
            }
        }
        return new ArrayList<>();
    }

    @Transactional(readOnly = true)
    public Optional<VacantDto> getById(String username, Integer id, String bearerToken) throws JsonProcessingException {
        String authUsername = logJwtService.getOnlyUsername(bearerToken);
        if (authUsername.equals(username)) {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                for (Vacant v : user.getFavoitesVacants()) {
                    if (Objects.equals(v.getId(), id)) {
                        return Optional.of(mapper.toVacantDto(v));
                    }
                }
            }
        }
        return Optional.empty();
    }

    @Transactional
    public Boolean save(String username, Integer id, String bearerToken) throws JsonProcessingException {
        String authUsername = logJwtService.getOnlyUsername(bearerToken);
        if (authUsername.equals(username)) {
            User user = userRepository.findByUsername(username);
            Optional<Vacant> vacant = vacantRepository.findByIdAndActiveIsTrue(id);
            if (user != null && vacant.isPresent()) {
                for (Vacant v : user.getFavoitesVacants()) {
                    if (Objects.equals(v.getId(), id)) {
                        return false;
                    }
                }
                user.getFavoitesVacants().add(vacant.get());
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public Boolean delete(String username, Integer id, String bearerToken) throws JsonProcessingException {
        String authUsername = logJwtService.getOnlyUsername(bearerToken);
        if (authUsername.equals(username)) {
            User user = userRepository.findByUsername(username);
            Optional<Vacant> vacant = vacantRepository.findByIdAndActiveIsTrue(id);
            if (user != null && vacant.isPresent()) {
                for (Vacant v : user.getFavoitesVacants()) {
                    if (Objects.equals(v.getId(), id)) {
                        user.getFavoitesVacants().remove(v);
                        userRepository.save(user);
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
}
