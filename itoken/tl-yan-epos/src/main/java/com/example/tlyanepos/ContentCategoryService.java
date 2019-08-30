package com.example.tlyanepos;

import java.util.List;

public interface ContentCategoryService {
    List<EUTreeNode> getCategoryList(long parentId);
    TreeNode getCategoryList2(long parentId);
}
