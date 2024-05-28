import { TestBed } from "@angular/core/testing";
import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";
import { UserService } from "./user.service";
import { User } from "@eps/shared/models/user.model";

describe('UserService', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });
    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return list of users', () => {
    const dummyUsers: User[] = [
      {
        _id: 'test-1',
        firstName: 'John',
        lastName: 'Doe',
        email: 'test-1',
        password: '123',
        role: 'admin'
      },
      {
        _id: 'test-2',
        firstName: 'Jane',
        lastName: 'Doe',
        email: 'test-1',
        password: '123',
        role: 'admin'
      }
    ];

    service.getUsers().subscribe(users => {
      expect(users.length).toBe(2);
      expect(users).toEqual(dummyUsers);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/all`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyUsers);
  });
});
