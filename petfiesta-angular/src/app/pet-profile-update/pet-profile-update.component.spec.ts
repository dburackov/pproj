import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetProfileUpdateComponent } from './pet-profile-update.component';

describe('PetProfileUpdateComponent', () => {
  let component: PetProfileUpdateComponent;
  let fixture: ComponentFixture<PetProfileUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetProfileUpdateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PetProfileUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
