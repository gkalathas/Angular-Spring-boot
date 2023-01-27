import {Component, ContentChild, OnInit, ViewChild} from '@angular/core';
import {ToitsuTableComponent} from '../../../toitsu-shared/toitsu-table/toitsu-table.component';
import {TranslateService} from '@ngx-translate/core';
import {DialogService} from 'primeng/dynamicdialog';
import {ActivatedRoute, Router} from '@angular/router';
import {Campaign} from '../../campaign';
import {campaignConsts} from '../../campaign.consts';

@Component({
  selector: 'app-campaign-list',
  templateUrl: './campaign-list.component.html',
  styleUrls: ['./campaign-list.component.scss']
})
export class CampaignListComponent implements OnInit {

  selectedCampaign: Campaign;

  displayAddModal: boolean = false;

  id: number;

  constructor(private translateService: TranslateService,
              private dialogService: DialogService,
              private activatedRoute: ActivatedRoute,
              private router: Router) { }

  @ViewChild(ToitsuTableComponent) table;


  url = campaignConsts.indexUrl;
  args = this.initializeArgs();

  viewLink = campaignConsts.viewLinkUrl;

  cols = [
    {
      field: 'extraActions',
      header: this.translateService.instant('extraActions'),
      sortField: '',
      width: 20,
      align: 'center'
    },
    {
      field: 'id',
      header: this.translateService.instant('id'),
      sortField: 'id',
      width: 20,
      align: 'center'
    },
    {
      field: 'name',
      header: this.translateService.instant('name'),
      sortField: 'name',
      width: 20,
      align: 'center'
    },
    {
      field: 'campaignTypeDescription',
      header: this.translateService.instant('campaignTypeDescription'),
      sortField: 'campaignTypeDescription',
      width: 20,
      align: 'center'
    },
    {
      field: 'campaignTypeKind',
      header: this.translateService.instant('campaignTypeKind'),
      sortField: 'campaignTypeKind',
      width: 20,
      align: 'center'
    },
    {
      field: 'campaignTypeKindLabel',
      header: this.translateService.instant('campaignTypeKindLabel'),
      sortField: 'campaignTypeKindLabel',
      width: 20,
      align: 'center'
    },
    {
      field: 'cost',
      header: this.translateService.instant('cost'),
      sortField: 'cost',
      width: 20,
      align: 'center'
    },
    {
      field: 'startDate',
      header: this.translateService.instant('startDate'),
      sortField: 'startDate',
      width: 20,
      align: 'center'
    },
    {
      field: 'endDate',
      header: this.translateService.instant('endDate'),
      sortField: 'endDate',
      width: 20,
      align: 'center'
    },
  ];

  ngOnInit(): void {
  }

  loadTableData() {
    this.table.loadTableDate();
  }

  loadComplete(responseData) {
    console.log(responseData);
  }

  loadError(responseError) {
    console.log(responseError);
  }
  clearArgs() {
    this.args = this.initializeArgs();
  }

  initializeArgs() {
    return {
      name: null,
      campaignTypeId: null,
      isRunning: null,
      startDateAfter: null,
      startDateBefore: null
    };
  }

  shoAddModal() {
    this.displayAddModal = true;
    this.selectedCampaign = null;
  }


  hideAddModal(isClosed: boolean) {
    this.displayAddModal = !isClosed;
  }

  viewModal(campaign: Campaign) {
    this.displayAddModal = true;
    this.selectedCampaign = campaign;
  }

  homePage() {
    this.router.navigate(['']);
  }

  rowclicked(rowData) {
    // this.viewModal(rowData);
    console.log(rowData);
    console.log(rowData.id);
    if (this.selectedCampaign) {
    }

  }


  onAddCampaign() {
    this.router.navigate(['/op/campaign/view']);
  }
}
