import { Component, OnInit } from '@angular/core';
import {CampaignService} from '../campaign-service';
import {CampaignModel} from '../../campaign.model';
import {TranslateService} from '@ngx-translate/core';
import {Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {ToitsuTableComponent} from '../../../toitsu-shared/toitsu-table/toitsu-table.component';
import {ConfirmationService, MessageService} from 'primeng/api';


@Component({
  selector: 'app-campaign-list',
  templateUrl: './campaign-list.component.html',
  styleUrls: ['./campaign-list.component.scss']
})
export class CampaignListComponent implements OnInit {


  subscription: Subscription;

  private toitsouTable: ToitsuTableComponent;

  displayAddModal: boolean = false;

  itemsPerPage: number = 10;
  
  selectedCampaign: CampaignModel;

  text: string = 'Add Campaigns';
  campaigns: CampaignModel[];
  campaigns2: CampaignModel[];
  campaign1: any;
  constructor(private campaignService: CampaignService,
              private translateService: TranslateService,
              private confirmationService: ConfirmationService,
              private messageService: MessageService,
              private router: Router) { }


  ngOnInit(): void {
    this.campaigns = this.campaignService.campaigns;


  }

  loadData(event: any) {
    this.campaignService.loadData(event).subscribe(
      data => {
        this.campaigns2 = data;
      }
    );

  }


  shoAddModal() {
    this.displayAddModal = true;
    this.selectedCampaign = null;
  }


  hideAddModal(isClosed: boolean) {
    this.displayAddModal = !isClosed;
  }

  showEditProduct(campaign: CampaignModel) {
    this.displayAddModal = true;
    this.selectedCampaign = campaign;
  }


  addCampaign() {
    this.router.navigate(['/gp/campaigns/addCampaign']);
  }


  deleteCampaign(campaignId: number) {
    this.confirmationService.confirm({
      message: 'Are you sure that you want to delete this product?',
      accept: () => {
        this.subscription = this.campaignService.deleteSingleCampaign(campaignId).subscribe(data => {
            console.log(data);
            this.messageService.add({severity: 'success', summary: 'success', detail: 'Product deleted'});
            // this.loadData();
          },
          error => {
            console.log('error occurred');
            this.messageService.add({severity: 'error', summary: 'Error', detail: error});
          });
      }
    });
  }
}
