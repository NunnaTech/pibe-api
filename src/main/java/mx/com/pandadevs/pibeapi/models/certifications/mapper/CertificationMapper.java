package mx.com.pandadevs.pibeapi.models.certifications.mapper;
// Mappers

import mx.com.pandadevs.pibeapi.models.aptitudes.Aptitude;
import mx.com.pandadevs.pibeapi.models.aptitudes.dto.AptitudeDto;
import mx.com.pandadevs.pibeapi.models.certifications.Certification;
import mx.com.pandadevs.pibeapi.models.certifications.dto.CertificationDto;
import mx.com.pandadevs.pibeapi.models.languages.dto.ResumeLanguageDto;
import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguage;
import mx.com.pandadevs.pibeapi.models.modes.Mode;
import mx.com.pandadevs.pibeapi.models.modes.dto.ModeDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CertificationMapper {

    CertificationMapper MAPPER = Mappers.getMapper( CertificationMapper.class);
    
    @Mappings({
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "active", target = "active"),
        @Mapping(source = "company", target = "company"),
        @Mapping(source = "obtainedDate", target = "expirationDate"),
        @Mapping(source = "expirationDate", target = "obtainedDate"),
    })
    CertificationDto toCertificationDto(Certification certification);
    List<CertificationDto> toCertificationsDto(List<Certification> certifications);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "resume", ignore = true)
    })
    Certification toCertification(CertificationDto certificationDto);

}
