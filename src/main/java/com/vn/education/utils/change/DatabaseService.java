package com.vn.education.utils.change;

import com.vn.education.dto.ChangeEvent;

public interface DatabaseService {

    void updateTargetDatabase(ChangeEvent changeEvent);

    void insertIntoTargetDatabase(ChangeEvent changeEvent);

    void deleteFromTargetDatabase(ChangeEvent changeEvent);
}
