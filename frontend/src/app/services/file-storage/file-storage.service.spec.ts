import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";
import { TestBed } from "@angular/core/testing";
import { FileStorageService } from "./file-storage.service";

describe('PersonnalService', () => {
  let service: FileStorageService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [FileStorageService]
    });
    service = TestBed.inject(FileStorageService);
    httpMock = TestBed.inject(HttpTestingController);
  });
});
