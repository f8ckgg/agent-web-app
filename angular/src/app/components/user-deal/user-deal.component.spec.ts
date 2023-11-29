import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDealComponent } from './user-deal.component';

describe('UserDealComponent', () => {
  let component: UserDealComponent;
  let fixture: ComponentFixture<UserDealComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserDealComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserDealComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
