package com.depromeet.oversweet.record.controller;

import com.depromeet.oversweet.record.dto.request.DrinkRecordSaveRequest;
import com.depromeet.oversweet.record.dto.response.DrinkRecordSaveResponse;
import com.depromeet.oversweet.record.service.DrinkRecordSaveService;
import com.depromeet.oversweet.response.DataResponse;
import com.depromeet.oversweet.response.MessageResponse;
import com.depromeet.oversweet.security.service.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "기록", description = "마신 음료 당 기록 관련 API")
@RestController
@RequestMapping("/api/v1/record")
@RequiredArgsConstructor
public class RecordController {

    private final DrinkRecordSaveService drinkRecordSaveService;

    /**
     * 마신 음료 당 기록
     * 추후 로그인 기능 구현 후, 로그인한 유저의 ID를 받아와야 함 (ex. @AuthenticationPrincipal User user)
     */
    @Operation(summary = "마신 음료 당 기록", description = "유저가 마신 음료의 당을 기록합니다.")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "마신 음료 당 기록 성공"))
    @SecurityRequirement(name = "accessToken")
    @PostMapping("/drink")
    public ResponseEntity<DataResponse<DrinkRecordSaveResponse>> saveDrinkRecord(
            @RequestBody @Valid final DrinkRecordSaveRequest drinkRecordSaveRequest,
            @AuthenticationPrincipal final CustomUserDetails userDetails
    ) {
        final DrinkRecordSaveResponse response = drinkRecordSaveService.saveDrinkRecord(userDetails.getId(), drinkRecordSaveRequest);
        return ResponseEntity.ok().body(DataResponse.of(HttpStatus.CREATED, "마신 음료 당 기록 성공", response));
    }

    @Operation(summary = "마신 음료 삭제", description = "유저가 마신 음료를 삭제할 수 있는 기능입니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "음료 기록 삭제 성공"))
    @SecurityRequirement(name = "accessToken")
    @DeleteMapping("/{recordId}")
    public ResponseEntity<MessageResponse> deleteDrinkRecord(
            @AuthenticationPrincipal final CustomUserDetails userDetails,
            @PathVariable @Parameter(description = "해당 레코드의 Id") final Long recordId
    ) {
        drinkRecordSaveService.deleteDrinkRecord(userDetails.getId(), recordId);
        return ResponseEntity.ok().body(MessageResponse.of(HttpStatus.OK, "마신 음료 기록 삭제 성공"));
    }
}
