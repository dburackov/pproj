import {Component, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgFor} from "@angular/common";
import {Tag} from "../entities/tag";
import {TagService} from "../services/tag.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-tag-update',
  standalone: true,
  imports: [FormsModule, NgFor],
  templateUrl: './tag-update.component.html'
})

export class TagUpdateComponent implements OnInit {
    tag: Tag = new Tag();
    id: number;

    constructor(private tagService: TagService,
                private router: Router,
                private activatedRoute: ActivatedRoute)
    {
        this.id = activatedRoute.snapshot.params["id"];
    }

    async ngOnInit() {
        this.tag = await this.tagService.getTag(this.id);
    }

    async updateTag() {
        await this.tagService.updateTag(this.id, this.tag);
        await this.router.navigate(["/tags"]);
    }
}
