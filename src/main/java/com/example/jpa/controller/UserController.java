package com.example.jpa.controller;


import com.example.jpa.model.User;
import com.example.jpa.dto.UserDto;
import com.example.jpa.service.UserService;

import com.example.jpa.dto.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

  private final UserService service;
  private final HttpServletRequest httpServletRequest;

  @GetMapping("")
  public Object fetchAll(@RequestParam(value = "current_page", defaultValue = "1", required = false) int currentPage,
                         @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
                         @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
                         @RequestParam(value = "sort_dir", defaultValue = "desc", required = false) String sortDir
  ) {

    Page<User> entities = service.fetchEntities(currentPage, pageSize, sortBy, sortDir);
    ResponseDto responseDto = ResponseDto.builder()
        .timestamp(new Date())
        .status(HttpStatus.CREATED)
        .path(httpServletRequest.getContextPath())
        .data(entities)
        .errors(null)
        .message("Process Successfully!")
        .build();
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);

  }

  @GetMapping("{id}")
  public Object fetchDetails(@RequestParam(value = "id") Long id) {
    User entity = service.fetchEntity(id);
    ResponseDto responseDto = ResponseDto.builder()
        .timestamp(new Date())
        .status(HttpStatus.OK)
        .path(httpServletRequest.getContextPath())
        .data(entity)
        .errors(null)
        .message("Process Successfully!")
        .build();
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }


@PostMapping("")
public Object createEntity(@RequestBody UserDto dto) {
  User entity= service.createEntity(dto);
  ResponseDto responseDto = ResponseDto.builder()
      .timestamp(new Date())
      .status(HttpStatus.CREATED)
      .path(httpServletRequest.getContextPath())
      .data(entity)
      .errors(null)
      .message("Process Successfully!")
      .build();
  return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
}

@PutMapping("{id}")
public Object update(@PathVariable("id")Long id,@RequestBody UserDto dto) {
  User entity= service.updateEntity(dto,id);
  ResponseDto responseDto = ResponseDto.builder()
      .timestamp(new Date())
      .status(HttpStatus.CREATED)
      .path(httpServletRequest.getContextPath())
      .data(entity)
      .errors(null)
      .message("Process Successfully!")
      .build();
  return ResponseEntity.status(HttpStatus.OK).body(responseDto);
}

@DeleteMapping("{id}")
public Object delete(@PathVariable("id") Long id) {
  User entity = service.deleteEntity(id);
  ResponseDto responseDto = ResponseDto.builder()
      .timestamp(new Date())
      .status(HttpStatus.OK)
      .path(httpServletRequest.getContextPath())
      .data(entity)
      .errors(null)
      .message("Process Successfully!")
      .build();
  return ResponseEntity.status(HttpStatus.OK).body(responseDto);
}
}
