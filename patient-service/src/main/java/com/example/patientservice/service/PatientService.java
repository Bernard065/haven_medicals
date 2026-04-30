package com.example.patientservice.service;

import com.example.patientservice.mapper.PatientMapper;
import com.example.patientservice.model.Patient;
import com.example.patientservice.repository.PatientRepository;
import com.example.patientservice.service.dto.PatientRequestDTO;
import com.example.patientservice.service.dto.PatientResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients () {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));

        return PatientMapper.toDTO(newPatient);
    }
}
