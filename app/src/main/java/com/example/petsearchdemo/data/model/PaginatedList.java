package com.example.petsearchdemo.data.model;

import java.util.ArrayList;
import java.util.List;

public class PaginatedList<ApiModel extends IViewModel<ViewModel>, ViewModel> {

    private List<ApiModel> results;

    public List<ViewModel> toListViewModel() {
        List<ViewModel> listViewModel = new ArrayList<>();
        for(ApiModel apiModel : results) {
            ViewModel vm = apiModel.toViewModel();
            if(vm != null) {
                listViewModel.add(vm);
            }
        }
        return listViewModel;
    }
}
