package br.com.ericson.gestao_vaga.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ericson.gestao_vaga.modules.company.entities.JobEntity;
import br.com.ericson.gestao_vaga.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {
        return jobRepository.save(jobEntity);
    }
}
