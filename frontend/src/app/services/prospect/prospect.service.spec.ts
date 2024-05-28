import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";
import { TestBed } from "@angular/core/testing";
import { ProspectService } from "./prospect.service";
import { Prospect } from "@eps/shared/models/prospect.model";

describe('ProspectService', () => {
  let service: ProspectService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ProspectService]
    });
    service = TestBed.inject(ProspectService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return list of prospects', () => {
    const dummyProspects: Prospect[] = [
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
        statusProspect: 'En attente',
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

    service.getProspects().subscribe(prospects => {
      expect(prospects.length).toBe(1);
      expect(prospects).toEqual(dummyProspects);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/all`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyProspects);
  });
});
