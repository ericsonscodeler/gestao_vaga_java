package br.com.ericson.gestao_vaga.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ericson.gestao_vaga.exceptions.CompanyNotFoundException;
import br.com.ericson.gestao_vaga.modules.company.entities.JobEntity;
import br.com.ericson.gestao_vaga.modules.company.repositories.CompanyRepository;
import br.com.ericson.gestao_vaga.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {

        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
            throw new CompanyNotFoundException();
        });
        return jobRepository.save(jobEntity);
    }
}
