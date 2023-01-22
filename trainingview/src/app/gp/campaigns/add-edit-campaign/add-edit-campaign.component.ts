import {Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output} from '@angular/core';
import {Subscription} from 'rxjs';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProductService} from '../../../cd/product-service';
import {MessageService} from 'primeng/api';
import {ActivatedRoute} from '@angular/router';
import {CampaignModel} from '../../campaign.model';
import {ToitsuTableService} from '../../../toitsu-shared/toitsu-table/toitsu-table.service';
import {CampaignService} from '../campaign-service';

@Component({
  selector: 'app-add-edit-campaign',
  templateUrl: './add-edit-campaign.component.html',
  styleUrls: ['./add-edit-campaign.component.scss']
})
export class AddEditCampaignComponent implements OnInit, OnChanges, OnDestroy {
  @Input()displayAddModal: boolean = true;
  @Input() selectedCampaign: CampaignModel = null;
  @Output()clickClose: EventEmitter<boolean> = new EventEmitter<boolean>();
  modalType: string;

  subscriptions: Subscription[] = [];

  subscription: Subscription;
  id: number;
  campaignForm: FormGroup;

  constructor(private fb: FormBuilder,
              private campaignService: CampaignService,
              private messageService: MessageService,
              private route: ActivatedRoute,
              private toitsouTableService: ToitsuTableService) { }

  ngOnInit(): void {
    this.initializeForm();
    // this.toitsouTable.loadTableData({})
  }

  ngOnChanges(): void {

    if (this.selectedCampaign) {
      this.modalType = 'Edit';
      this.id = this.route.snapshot.params['id'];
      this.campaignForm.patchValue(this.selectedCampaign);

    } else {
      this.campaignForm.reset();
      this.modalType = 'Add';
    }
  }


  initializeForm() {
    this.campaignForm = this.fb.group({
      name: ['', Validators.required],
      campaignTypeDescription: [''],
      campaignTypeKind: [''],
      cost: [0, Validators.required],
      startDate: [Date, Validators.required],
      endDate: [Date, Validators.required]
    });

  }


  closeModal() {
    this.clickClose.emit(true);
  }



  addCampaign() {
    this.subscription = this.campaignService.createCampaign(this.campaignForm.value).subscribe(
      response => {
        console.log(response);
        this.campaignForm.reset();
        this.clickClose.emit(true);
        this.messageService.add({severity: 'success', summary: 'Success', detail: 'Product Added'});
      },
      error => {
        console.log('error occurred');
        this.messageService.add({severity: 'error', summary: 'Error', detail: error});
      }
    );
    this.subscriptions.push(this.subscription);
  }
  //
  // onUpdateProduct() {
  //   this.subscription = this.productService.updateProduct(this.id, this.selectedProduct).subscribe(
  //     response => {
  //       console.log(response);
  //       this.campaignForm.reset();
  //       this.clickClose.emit(true);
  //       this.messageService.add({severity: 'success', summary: 'success', detail: 'Product Updated'});
  //     },
  //     error => {
  //       console.log('error occurred');
  //       this.messageService.add({severity: 'error', summary: 'Error', detail: error});
  //     }
  //   );
  //   this.subscriptions.push(this.subscription);
  // }



  ngOnDestroy(): void {

    // this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  onUpdateCampaign() {

  }
}
