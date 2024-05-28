import { ComponentFixture, TestBed } from "@angular/core/testing";
import { RouterTestingModule } from "@angular/router/testing";

import { ListProspectsComponent } from "./list-prospects.component";

describe('ListProspectsComponent', () => {
  let component: ListProspectsComponent;
  let fixture: ComponentFixture<ListProspectsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [ListProspectsComponent],
    }).compileComponents();
    fixture = TestBed.createComponent(ListProspectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
