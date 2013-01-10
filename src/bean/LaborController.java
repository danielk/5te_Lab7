package bean;

import java.util.Comparator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import model.Schueler;

@ManagedBean(name = "laborController")
@SessionScoped
public class LaborController {

    private List<Schueler> schueler_list;
    private TreeNode root;
    private TreeNode nodeAH;
    private TreeNode nodeIZ;
    private TreeNode selectedNode;

    private String firstname = "test";
    private String lastname = "test";
    private boolean weibl = true;

    private String ausg_firstname = "Noch kein Sch�ler Ausgew�hlt";
    private String ausg_lastname = "leer";
    private String ausg_geschl = "leer";
    private int ausg_kn = 0;


    public List<Schueler> getSchueler_list() {
        return schueler_list;
    }

    public void setSchueler_list(List<Schueler> schueler_list) {
        this.schueler_list = schueler_list;
    }

    public LaborController() {
        selectedNode = null;
        populateTree();
    }

    // Muss ich leider so (h�ndisch) machen  anscheinend
    // http://forum.primefaces.org/viewtopic.php?f=3&t=24471
    private void populateTree() {
        schueler_list = Schueler.getKlasse();
        root = new DefaultTreeNode("Root", null);
        nodeAH = new DefaultTreeNode("A - H", getRoot());
        nodeIZ = new DefaultTreeNode("I - Z", getRoot());

        java.util.Collections.sort(schueler_list, new Comparator<Schueler>() {
            @Override
            public int compare(Schueler o1, Schueler o2) {
                if (o1.getNr() > o2.getNr())
                    return 1;
                else if (o1.getNr() == o2.getNr())
                    return 0;
                else
                    return -1;
            }
        });

        for (Schueler schueler : schueler_list) {

            char[] ch = schueler.getVorname().trim().toUpperCase().toCharArray();
            if (ch[0] <= 'H')
                new DefaultTreeNode(schueler, nodeAH);
            else if (ch[0] > 'H')
                new DefaultTreeNode(schueler, nodeIZ);
        }

        nodeAH.setExpanded(true);
        nodeIZ.setExpanded(true);


    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void onNodeSelect(NodeSelectEvent e) {

        setSelectedNode(e.getTreeNode());
        FacesContext context = FacesContext.getCurrentInstance();
        e.getTreeNode().setExpanded(true);
        if (!(e.getTreeNode()
                .getData() instanceof Schueler))
            context.addMessage(null, new FacesMessage("Successful", e.getTreeNode().getChildCount() + " Sch�ler unter dem Knoten"));
        else
            renderSchueler((Schueler) e.getTreeNode()
                    .getData());

    }

    private void renderSchueler(Schueler s) {
        this.ausg_firstname = s.getVorname();
        this.ausg_lastname = s.getNachname();
        this.ausg_geschl = s.getGeschlecht() + "";
        this.ausg_kn = s.getNr();
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void saveSchueler() {
        if (this.isWeibl()) new Schueler(this.getLastname(), this.getFirstname(), 'W');
        if (!this.isWeibl()) new Schueler(this.getLastname(), this.getFirstname(), 'M');
        populateTree();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isWeibl() {
        return weibl;
    }

    public void setWeibl(boolean weibl) {
        this.weibl = weibl;
    }

    public String getAusg_firstname() {
        return ausg_firstname;
    }

    public void setAusg_firstname(String ausg_firstname) {
        this.ausg_firstname = ausg_firstname;
    }

    public String getAusg_lastname() {
        return ausg_lastname;
    }

    public void setAusg_lastname(String ausg_lastname) {
        this.ausg_lastname = ausg_lastname;
    }

    public String getAusg_geschl() {
        return ausg_geschl;
    }

    public void setAusg_geschl(String ausg_geschl) {
        this.ausg_geschl = ausg_geschl;
    }

    public int getAusg_kn() {
        return ausg_kn;
    }

    public void setAusg_kn(int ausg_kn) {
        this.ausg_kn = ausg_kn;
    }

}

//v