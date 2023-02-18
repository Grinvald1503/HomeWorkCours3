package com.example.homeworkcourse3.Controllers;

import com.example.homeworkcourse3.services.IngredientFilesService;
import com.example.homeworkcourse3.services.RecipeFileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {
    private final RecipeFileService recipeFileService;
    private final IngredientFilesService ingredientFilesService;

    public FilesController(RecipeFileService recipeFileService, IngredientFilesService ingredientFilesService) {
        this.recipeFileService = recipeFileService;
        this.ingredientFilesService = ingredientFilesService;
    }

    @GetMapping("/exportrec")
    public ResponseEntity<InputStreamResource> dowLoadDataFile() throws FileNotFoundException {
        File file = recipeFileService.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/importrec", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFileRecipe(@RequestParam MultipartFile file) {
        recipeFileService.cleanDataFileRecipe();
        File dataFile = recipeFileService.getDataFile();

        return uploadFile(dataFile, file);
    }
    @PostMapping(value = "/importing", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFileIngredient(@RequestParam MultipartFile file) {
        ingredientFilesService.cleanDataFileIngredient();
        File dataFile = ingredientFilesService.getDataFile();
        return uploadFile(dataFile, file);
    }
    public ResponseEntity<Void> uploadFile(File dataFile, MultipartFile file) {

        try (FileOutputStream fos = new FileOutputStream(dataFile)){
            org.apache.commons.io.IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
