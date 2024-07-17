import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";
import { TestBed } from "@angular/core/testing";
import { PersonnalService } from "./personnal.service";
import { Personnal } from "@gtper/shared/models/personnal.model";

describe('PersonnalService', () => {
  let service: PersonnalService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PersonnalService]
    });
    service = TestBed.inject(PersonnalService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return list of personnals', () => {
    const dummyPersonnals: Personnal[] = [
      {
        _id: '123',
        firstName: 'John',
        lastName: 'Doe',
        trigramme: 'jde',
        email: 'john.doe@example.com',
        phone: '123456789',
        profil: 'Développeur Fullstack',
        dateContact: new Date(),
        dateEntretien: new Date(),
        statusPersonnal: 'En attente',
        bum: {
          _id: 'test-1',
          firstName: 'John',
          lastName: 'Doe',
          email: 'test-1',
          password: '123',
          role: 'admin'
        },
        rh: {
          _id: 'test-1',
          firstName: 'John',
          lastName: 'Doe',
          email: 'test-1',
          password: '123',
          role: 'admin'
        },
        source: 'linkedin',
        prententionSalarial: 12,
        niveauEtude: 'test-1',
        disponibilite: 'test-1',
        mobiliteGeo: 'test-1',
        pr: 12,
        pushQualif: {
          client: 'client-1',
          datePush: new Date(),
          datePushRetour: new Date(),
          dateQualif: new Date()
        },
        cv: 'undefined',
        grille: 'undefined',
        dc: 'undefined'
      }
    ];

    service.getPersonnals().subscribe(personnals => {
      expect(personnals.length).toBe(1);
      expect(personnals).toEqual(dummyPersonnals);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/all`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyPersonnals);
  });
});
