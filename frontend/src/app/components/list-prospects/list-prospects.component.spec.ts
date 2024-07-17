import { ComponentFixture, TestBed } from "@angular/core/testing";
import { RouterTestingModule } from "@angular/router/testing";

import { ListPersonnalsComponent } from "./list-personnals.component";

describe('ListPersonnalsComponent', () => {
  let component: ListPersonnalsComponent;
  let fixture: ComponentFixture<ListPersonnalsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [ListPersonnalsComponent],
    }).compileComponents();
    fixture = TestBed.createComponent(ListPersonnalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
