package com.bloomberg.fx.deals.controller;

import com.bloomberg.fx.deals.message.FeedbackMessage;
import com.bloomberg.fx.deals.model.Deal;
import com.bloomberg.fx.deals.reader.DataReader;
import com.bloomberg.fx.deals.service.DataReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/data")
public class UploadController {
    @Autowired
    DataReaderService dataReaderService;

    @PostMapping("/upload")
    public ResponseEntity<FeedbackMessage> uploadFile(@RequestParam("file") MultipartFile file){
        String response = "";

        if(DataReader.checkExcelFormat(file)){
            try{
                dataReaderService.save(file);
                response = "File: "+file.getOriginalFilename()+" Uploaded Successfully!";
                return ResponseEntity.status(HttpStatus.OK).body(new FeedbackMessage(response));
            }catch(Exception e){
                response = "Failed to upload File: "+file.getOriginalFilename()+"!";
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FeedbackMessage(response));
            }
        }
        response = "Please upload a valid excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FeedbackMessage(response));
    }

    @GetMapping("/deals")
    public ResponseEntity<List<Deal>> getAllDeals(){
        try{
            List<Deal> deals = dataReaderService.getAllDeals();
            if(deals.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(deals, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
