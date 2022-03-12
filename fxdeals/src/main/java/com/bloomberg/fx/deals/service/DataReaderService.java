package com.bloomberg.fx.deals.service;

import com.bloomberg.fx.deals.model.Deal;
import com.bloomberg.fx.deals.reader.DataReader;
import com.bloomberg.fx.deals.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DataReaderService {
    @Autowired
    DealRepository repository;

    public void save(MultipartFile file){
        try{
            List<Deal> deals = DataReader.readExcelFile(file.getInputStream());
            repository.saveAll(deals);
        }catch(IOException e){
            throw new RuntimeException("failed to upload and save excel data: " + e.getMessage());
        }
    }

    public List<Deal> getAllDeals(){
        return repository.findAll();
    }

}
