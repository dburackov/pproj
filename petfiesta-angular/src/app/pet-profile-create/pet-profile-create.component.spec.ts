import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetProfileCreateComponent } from './pet-profile-create.component';

describe('PetProfileCreateComponent', () => {
  let component: PetProfileCreateComponent;
  let fixture: ComponentFixture<PetProfileCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetProfileCreateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PetProfileCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
