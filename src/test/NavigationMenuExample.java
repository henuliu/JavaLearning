package test;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class NavigationMenuExample extends JFrame {
    private JTree navigationTree;

    public NavigationMenuExample() {
        // 创建根节点
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
        
        // 创建一级节点
        DefaultMutableTreeNode contactNode = new DefaultMutableTreeNode("Contacts");
        DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode("Groups");
        rootNode.add(contactNode);
        rootNode.add(groupNode);
        
        // 创建二级节点
        DefaultMutableTreeNode friendNode = new DefaultMutableTreeNode("Friends");
        DefaultMutableTreeNode familyNode = new DefaultMutableTreeNode("Family");
        contactNode.add(friendNode);
        contactNode.add(familyNode);

        // 创建导航树
        navigationTree = new JTree(rootNode);
        JScrollPane scrollPane = new JScrollPane(navigationTree);

        // 添加导航树的选择事件监听器
        navigationTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) navigationTree.getLastSelectedPathComponent();
                if (selectedNode != null) {
                    String selectedNodeName = selectedNode.toString();
                    // 在此处处理选择节点的逻辑，例如显示相应的联系人列表
                    JOptionPane.showMessageDialog(null, "Selected: " + selectedNodeName);
                }
            }
        });

        // 将导航树添加到窗口中
        getContentPane().add(scrollPane, BorderLayout.WEST);

        // 设置窗口的标题和大小，并使其可见
        setTitle("Navigation Menu Example");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new NavigationMenuExample();
    }
}
