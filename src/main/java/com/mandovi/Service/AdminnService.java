package com.mandovi.Service;

import com.mandovi.Entity.Adminn;

import java.util.List;

public interface AdminnService {

    public Adminn adminnLogin (String adminnId, String adminnPassword);

    public Adminn registerAdminn (Adminn adminn);

}

