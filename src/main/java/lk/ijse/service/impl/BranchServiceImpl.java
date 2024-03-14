package lk.ijse.service.impl;

import lk.ijse.dto.BranchDto;
import lk.ijse.entity.Branch;
import lk.ijse.repository.BranchRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.service.BranchService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BranchServiceImpl implements BranchService {
    private Session session;
    BranchRepository branchRepository = (BranchRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BRANCH);
    @Override
    public boolean save(BranchDto branchDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            branchRepository.setSession(session);
            branchRepository.save(branchDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<BranchDto> getAll() {
        session = SessionFactoryConfig.getInstance().getSession();
        branchRepository.setSession(session);
        List<Branch> all = branchRepository.getAll();
        List<BranchDto> dtoList = new ArrayList<>();
        for (Branch branch : all){
            dtoList.add(branch.toDto());
        }
        return dtoList;
    }

    @Override
    public boolean update(BranchDto branchDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            branchRepository.setSession(session);
            branchRepository.update(branchDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(BranchDto branchDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            branchRepository.setSession(session);
            branchRepository.delete(branchDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }
}
