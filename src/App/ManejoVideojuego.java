
package App;

import bd.DAOVideojuego;
import javax.swing.table.DefaultTableModel;


public class ManejoVideojuego extends javax.swing.JFrame {
    
    
    DefaultTableModel oModelo = new DefaultTableModel();
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ManejoVideojuego.class.getName());

    /**
     * Creates new form ManejoVideojuego
     */
    public ManejoVideojuego() {
        initComponents();
        
    // 1. Cargar empresas en Insertar
        cmbEmpresa.removeAllItems();
        cmbEmpresa.addItem("Seleccione empresa...");
        cmbEmpresa.addItem("EA");
        cmbEmpresa.addItem("Rockstar");
        cmbEmpresa.addItem("Ubisoft");
        cmbEmpresa.addItem("Sony");
        cmbEmpresa.addItem("Team Cherry");
        cmbEmpresa.addItem("Naughty Dog");
        cmbEmpresa.addItem("Valve");
        cmbEmpresa.addItem("Riot Games");
        cmbEmpresa.addItem("Blizzard");
        cmbEmpresa.addItem("Bethesda");
        cmbEmpresa.addItem("Nintendo");

        rbFisico.setSelected(true);
        cmbTitulo.removeAllItems();

        // 2. Copiar las mismas empresas a Actualizar 
        cmbEmpresaAct.removeAllItems();
        for (int i = 0; i < cmbEmpresa.getItemCount(); i++) {
            cmbEmpresaAct.addItem(cmbEmpresa.getItemAt(i));
        }
        rbFisicoAct.setSelected(true);
        cmbTituloAct.removeAllItems();

        // 3. Configurar columnas de la tabla 
        oModelo.addColumn("ID");
        oModelo.addColumn("Título");
        oModelo.addColumn("Precio (c/IVA)");
        oModelo.addColumn("Detalle");
        oModelo.addColumn("Empresa");
        tblVideojuegos.setModel(oModelo);

        // 4. Escuchar cambios en los ComboBoxes (que serian Insertar y Actualizar)
        cmbEmpresa.addActionListener(e -> actualizarVideojuegosSegunEmpresa());
        cmbTitulo.addActionListener(e -> calcularPrecioPorTitulo());
        cmbEmpresaAct.addActionListener(e -> actualizarVideojuegosSegunEmpresaAct());
        cmbTituloAct.addActionListener(e -> calcularPrecioPorTituloAct());

        // 5. Cargar datos iniciales desde MySQL
        cargarTabla();
    }

    public void cargarTabla() {
        try {
            // 1. Limpia todas las filas actuales para evitar que se dupliquen al recargar
            oModelo.setRowCount(0);

            // 2. Trae la lista de videojuegos desde la bd
            bd.DAOVideojuego dao = new bd.DAOVideojuego();
            java.util.List<Model.Videojuego> lista = dao.listarVideojuegos();

            // 3. Recorre cada videojuego y lo agrega como una fila en el modelo
            for (Model.Videojuego v : lista) {
                Object[] fila = new Object[5];
                fila[0] = v.getId_videojuego();
                fila[1] = v.getTitulo_videojuego();
                fila[2] = v.getPrecio_videojuego();
                fila[3] = v.getDetalle_videojuego();
                fila[4] = v.getEmpresa_dev_videojuego();
                
                oModelo.addRow(fila);
            }
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al cargar los datos en la tabla: " + e.getMessage());
        }
    }
   private void actualizarVideojuegosSegunEmpresa() {
        cargarTitulosPorEmpresa(cmbEmpresa, cmbTitulo);
        if (cmbEmpresa.getSelectedItem() == null || cmbEmpresa.getSelectedItem().toString().equals("Seleccione empresa...")) {
            txtPrecio.setText("");
        }
    }

    private void actualizarVideojuegosSegunEmpresaAct() {
        cargarTitulosPorEmpresa(cmbEmpresaAct, cmbTituloAct);
        if (cmbEmpresaAct.getSelectedItem() == null || cmbEmpresaAct.getSelectedItem().toString().equals("Seleccione empresa...")) {
            txtPrecioAct.setText("");
        }
    }

    private void cargarTitulosPorEmpresa(javax.swing.JComboBox<String> comboEmpresa, javax.swing.JComboBox<String> comboTitulo) {
        if (comboEmpresa.getSelectedItem() == null) return;

        String empresa = comboEmpresa.getSelectedItem().toString();
        comboTitulo.removeAllItems();

        if (empresa.equals("Seleccione empresa...")) {
            return;
        }

        cmbTitulo.addItem("Seleccione título...");
//Lista de Titulos en base a la empresa desarrolladora
        switch (empresa) {
            case "EA":
                cmbTitulo.addItem("EA Sports FC 26");
                cmbTitulo.addItem("EA Sports FC 25");
                cmbTitulo.addItem("EA Sports FC 24");
                cmbTitulo.addItem("EA Sports FC 23");
                cmbTitulo.addItem("EA Sports FC 22");
                cmbTitulo.addItem("EA Sports FC 21");
                cmbTitulo.addItem("EA Sports FC 20");
                cmbTitulo.addItem("EA Sports FC 19");
                cmbTitulo.addItem("EA Sports FC 18");
                cmbTitulo.addItem("EA Sports FC 17");
                cmbTitulo.addItem("EA Sports FC 16");
                cmbTitulo.addItem("EA Sports FC 15");
                cmbTitulo.addItem("EA Sports FC 14");
                cmbTitulo.addItem("The Sims 4");
                break;
            case "Rockstar":
                cmbTitulo.addItem("Grand Theft Auto VI");
                cmbTitulo.addItem("Grand Theft Auto V");
                cmbTitulo.addItem("Grand Theft Auto IV");
                cmbTitulo.addItem("Grand Theft Auto San Andreas");
                cmbTitulo.addItem("Red Dead Redemption 2");
                break;
            case "Ubisoft":
                cmbTitulo.addItem("Assassin's Creed Valhalla");
                cmbTitulo.addItem("Far Cry 6");
                break;
            case "Sony":
                cmbTitulo.addItem("God of War Ragnarok");
                cmbTitulo.addItem("Concord");
                cmbTitulo.addItem("Wolverine");
                cmbTitulo.addItem("Spider-Man 2");
                cmbTitulo.addItem("Spider-Man ");
                break;
            case "Team Cherry":
                cmbTitulo.addItem("Hollow Knight");
                cmbTitulo.addItem("Hollow Knight: Silksong");
                break;
            case "Naughty Dog":
                cmbTitulo.addItem("The Last of Us Part I");
                cmbTitulo.addItem("The Last of Us Part II");
                cmbTitulo.addItem("Uncharted 4");
                break;
            case "Valve":
                cmbTitulo.addItem("Half-Life ");
                cmbTitulo.addItem("Half-Life 2");
                cmbTitulo.addItem("Dota 2");
                cmbTitulo.addItem("Portal 2");
                cmbTitulo.addItem("Counter Strike");
                break;
            case "Riot Games":
                cmbTitulo.addItem("Valorant Points Pack");
                cmbTitulo.addItem("LOL");
                cmbTitulo.addItem("Ruined King");
                break;
            case "Blizzard":
                cmbTitulo.addItem("Diablo IV");
                cmbTitulo.addItem("Overwatch 2 Pack");
                cmbTitulo.addItem("WOW");
                break;
            case "Bethesda":
                cmbTitulo.addItem("The Elder Scrolls V: Skyrim");
                cmbTitulo.addItem("Starfield");
                break;
            case "Nintendo":
                cmbTitulo.addItem("The Legend of Zelda: Tears of the Kingdom");
                cmbTitulo.addItem("Super Mario Odyssey");
                break;
        }
    }

    private int obtenerPrecioConIva(String titulo) {
        int precioNeto = 0;
        
        switch (titulo) {
            case "EA Sports FC 26":
            case "EA Sports FC 25":
            case "EA Sports FC 24":
            case "EA Sports FC 23":
            case "EA Sports FC 22":
            case "EA Sports FC 21":
            case "EA Sports FC 20":
            case "EA Sports FC 19":
            case "EA Sports FC 18":
            case "EA Sports FC 17":
            case "EA Sports FC 16":
            case "Grand Theft Auto VI":
            case "Grand Theft Auto V":
            case "Grand Theft Auto IV":
            case "Grand Theft Auto San Andreas":
            case "Red Dead Redemption 2":
            case "God of War Ragnarok":
            case "Concord":    
            case "Wolverine":
            case "Spider-Man 2":
            case "Spider-Man ":
            case "The Last of Us Part I":
            case "The Last of Us Part II":
            case "Diablo IV":
            case "Starfield":
            case "The Legend of Zelda: Tears of the Kingdom":
            case "Super Mario Odyssey":
                precioNeto = 50000;
                break;
            case "Assassin's Creed Valhalla":
            case "Far Cry 6":
            case "Uncharted 4":
            case "The Elder Scrolls V: Skyrim":
                precioNeto = 35000;
                break;
            case "Hollow Knight":
            case "Hollow Knight: Silksong":
            case "Half-Life ":
            case "Half-Life 2":
            case "Portal 2":
            case "Ruined King":
            case "Counter Strike":
                precioNeto = 15000;
                break;
            case "The Sims 4":
            case "Valorant Points Pack":
            case "Overwatch 2 Pack":
            case"WOW":  
            case"Dota 2":    
                 
                precioNeto = 20000;
                break;
        }
        
        // Retorna el precio neto + 19% de IVA redondeado a entero
        return (int) Math.round(precioNeto * 1.19);
    }

    private void calcularPrecioPorTitulo() {
        if (cmbTitulo.getSelectedItem() == null) return;
        String titulo = cmbTitulo.getSelectedItem().toString();
        
        if (titulo.equals("Seleccione título...")) {
            txtPrecio.setText("");
            return;
        }
        
        int precioFinal = obtenerPrecioConIva(titulo);
        txtPrecio.setText(String.valueOf(precioFinal)); // Muestra el precio automático con IVA
    
      
    }
    
    private void calcularPrecioPorTituloAct() {
        if (cmbTituloAct.getSelectedItem() == null) {
            txtPrecioAct.setText("");
            return;
        }
        String titulo = cmbTituloAct.getSelectedItem().toString();

        if (titulo.equals("Seleccione título...")) {
            txtPrecioAct.setText("");
            return;
        }

        int precioFinal = obtenerPrecioConIva(titulo);
        txtPrecioAct.setText(String.valueOf(precioFinal));
    }
    private String obtenerDetalleJson(String titulo, String tipo) {
        String clasificacion = "Todo público";
        String categoria = "Aventura";

        switch (titulo) {
            // EA
            case "EA Sports FC 26":
            case "EA Sports FC 25":
            case "EA Sports FC 24":
            case "EA Sports FC 23":
            case "EA Sports FC 22":
            case "EA Sports FC 21":
            case "EA Sports FC 20":
            case "EA Sports FC 19":
            case "EA Sports FC 18":
            case "EA Sports FC 17":
            case "EA Sports FC 16":
            case "EA Sports FC 15":
            case "EA Sports FC 14":
                clasificacion = "Todo público";
                categoria = "Deportes";
                break;
            case "The Sims 4":
                clasificacion = "+12";
                categoria = "Simulación";
                break;

            // Rockstar
            case "Grand Theft Auto VI":
            case "Grand Theft Auto V":
            case "Grand Theft Auto IV":
            case "Grand Theft Auto San Andreas":
                clasificacion = "+18";
                categoria = "Acción / Mundo Abierto";
                break;
            case "Red Dead Redemption 2":
                clasificacion = "+18";
                categoria = "Aventura / Western";
                break;

            // Ubisoft
            case "Assassin's Creed Valhalla":
                clasificacion = "+18";
                categoria = "RPG / Acción";
                break;
            case "Far Cry 6":
                clasificacion = "+18";
                categoria = "Shooter";
                break;

            // Sony
            case "God of War Ragnarok":
                clasificacion = "+18";
                categoria = "Acción / Aventura";
                break;
            case "Concord":
                clasificacion = "+12";
                categoria = "Shooter";
                break;
            case "Wolverine":
                clasificacion = "+18";
                categoria = "Acción";
                break;
            case "Spider-Man 2":
            case "Spider-Man ":
                clasificacion = "+12";
                categoria = "Acción / Aventura";
                break;

            // Team Cherry
            case "Hollow Knight":
            case "Hollow Knight: Silksong":
                clasificacion = "+12";
                categoria = "Metroidvania";
                break;

            // Naughty Dog
            case "The Last of Us Part I":
            case "The Last of Us Part II":
                clasificacion = "+18";
                categoria = "Supervivencia / Acción";
                break;
            case "Uncharted 4":
                clasificacion = "+16";
                categoria = "Aventura";
                break;

            // Valve
            case "Half-Life ":
            case "Half-Life 2":
            case "Counter Strike":
                clasificacion = "+16";
                categoria = "Shooter";
                break;
            case "Portal 2":
                clasificacion = "+12";
                categoria = "Puzzles";
                break;
            case "Dota 2":
                clasificacion = "+12";
                categoria = "MOBA";
                break;

            // Riot Games
            case "Valorant Points Pack":
                clasificacion = "+16";
                categoria = "Shooter Táctico";
                break;
            case "LOL":
                clasificacion = "+12";
                categoria = "MOBA";
                break;
            case "Ruined King":
                clasificacion = "+12";
                categoria = "RPG por turnos";
                break;

            // Blizzard
            case "Diablo IV":
                clasificacion = "+18";
                categoria = "Action RPG";
                break;
            case "Overwatch 2 Pack":
                clasificacion = "+12";
                categoria = "Hero Shooter";
                break;
            case "WOW":
                clasificacion = "+12";
                categoria = "MMORPG";
                break;

            // Bethesda
            case "The Elder Scrolls V: Skyrim":
                clasificacion = "+18";
                categoria = "RPG";
                break;
            case "Starfield":
                clasificacion = "+16";
                categoria = "RPG / Ciencia Ficción";
                break;

            // Nintendo
            case "The Legend of Zelda: Tears of the Kingdom":
                clasificacion = "+12";
                categoria = "Aventura / Mundo Abierto";
                break;
            case "Super Mario Odyssey":
                clasificacion = "Todo público";
                categoria = "Plataformas";
                break;
        }

        return "{"
                + "\"clasificacion_edad\": \"" + clasificacion + "\", "
                + "\"categoria\": \"" + categoria + "\", "
                + "\"tipo\": \"" + tipo + "\""
                + "}";
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTextField1 = new javax.swing.JTextField();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        pnlInsertar = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbEmpresa = new javax.swing.JComboBox<>();
        cmbTitulo = new javax.swing.JComboBox<>();
        txtPrecio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDetalle = new javax.swing.JTextField();
        rbDigital = new javax.swing.JRadioButton();
        rbFisico = new javax.swing.JRadioButton();
        btnVolver = new javax.swing.JButton();
        BtnInsertar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbEmpresaAct = new javax.swing.JComboBox<>();
        cmbTituloAct = new javax.swing.JComboBox<>();
        rbFisicoAct = new javax.swing.JRadioButton();
        rbDigitalAct = new javax.swing.JRadioButton();
        btnActualizar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtPrecioAct = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtIdVideojuego = new javax.swing.JTextField();
        btnBorrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtEmpresaBuscar = new javax.swing.JTextField();
        txtVideojuegoBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        rbFisicoBuscar = new javax.swing.JRadioButton();
        rbDigitalBuscar = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVideojuegos = new javax.swing.JTable();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Seleccione la Empresa");

        jLabel3.setText("Seleccione el Videojuego");

        jLabel2.setText("Precio del Videojuego");

        cmbEmpresa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbTitulo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtPrecio.setText("Automatico al seleccionar un videojuego");

        jLabel4.setText("Detalles del videojuego");

        txtDetalle.setText("Los detalles extra del videojuego se mostraran en la tabla");

        buttonGroup1.add(rbDigital);
        rbDigital.setText("Digital");

        buttonGroup1.add(rbFisico);
        rbFisico.setText("Fisico");

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        BtnInsertar.setText("Insertar");
        BtnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInsertarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbFisico, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addComponent(rbDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                                    .addComponent(txtPrecio)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbTitulo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbEmpresa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(99, 99, 99)
                .addComponent(BtnInsertar)
                .addGap(91, 91, 91))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnVolver)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnVolver)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbDigital)
                            .addComponent(rbFisico))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, Short.MAX_VALUE)
                        .addComponent(BtnInsertar)
                        .addGap(60, 60, 60))))
        );

        pnlInsertar.addTab("Insertar", jPanel1);

        jLabel5.setText("Seleccione la Empresa a actualizar");

        jLabel6.setText("Seleccione el videojuego quiera actulizar");

        cmbEmpresaAct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbTituloAct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonGroup2.add(rbFisicoAct);
        rbFisicoAct.setText("Fisico");

        buttonGroup2.add(rbDigitalAct);
        rbDigitalAct.setText("Digital");

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel7.setText("Precio del Videojuego");

        txtPrecioAct.setText("Automatico al seleccionar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActualizar)
                .addGap(164, 164, 164))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rbFisicoAct, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(rbDigitalAct, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbEmpresaAct, 0, 175, Short.MAX_VALUE)
                                    .addComponent(cmbTituloAct, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(txtPrecioAct, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(363, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbEmpresaAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbTituloAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPrecioAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbFisicoAct)
                    .addComponent(rbDigitalAct))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActualizar)
                .addGap(31, 31, 31))
        );

        pnlInsertar.addTab("Actualizar", jPanel2);

        jLabel8.setText("Ingrese id del Videojuego que desee eliminar");

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBorrar)
                .addGap(142, 142, 142))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel8)
                .addGap(85, 85, 85)
                .addComponent(txtIdVideojuego, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtIdVideojuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(btnBorrar)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pnlInsertar.addTab("Borrar", jPanel3);

        jLabel9.setText("Seleccione la empresa a buscar");

        jLabel10.setText("Seleccione el videojuego a buscar");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        buttonGroup3.add(rbFisicoBuscar);
        rbFisicoBuscar.setText("Fisico");

        buttonGroup3.add(rbDigitalBuscar);
        rbDigitalBuscar.setText("Digital");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                .addGap(87, 87, 87)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmpresaBuscar)
                    .addComponent(txtVideojuegoBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(rbFisicoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122)
                .addComponent(rbDigitalBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addGap(164, 164, 164))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtEmpresaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtVideojuegoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(rbFisicoBuscar)
                    .addComponent(rbDigitalBuscar))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pnlInsertar.addTab("Buscar", jPanel4);

        tblVideojuegos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVideojuegos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVideojuegosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVideojuegos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlInsertar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(320, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        Login oLogin = new Login();
        oLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void BtnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInsertarActionPerformed
                                                
        try {
            // 1. Validar que se haya seleccionado una empresa y un título válido
            if (cmbEmpresa.getSelectedIndex() <= 0 || cmbTitulo.getSelectedIndex() <= 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona una empresa y un videojuego válido.");
                return;
            }

            // 2. Validar que alguno de los dos rb esté marcado
            if (!rbFisico.isSelected() && !rbDigital.isSelected()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona si el videojuego es Físico o Digital.");
                return;
            }

            // 3. Capturar los datos seleccionados por el usuario
            String empresaDev = cmbEmpresa.getSelectedItem().toString();
            String titulo = cmbTitulo.getSelectedItem().toString();
            String tipo = rbFisico.isSelected() ? "Físico" : "Digital";

            // 4. Calcular precio automático con IVA y el json
            int precioConIva = obtenerPrecioConIva(titulo);
            String detalleJson = obtenerDetalleJson(titulo, tipo);

            // 5. Guardar el registro en la base de datos MySQL
            bd.DAOVideojuego dao = new bd.DAOVideojuego();
            dao.insertarVideojuego(titulo, precioConIva, detalleJson, empresaDev);

            javax.swing.JOptionPane.showMessageDialog(this, "Videojuego agregado correctamente.");

            // 6. Reiniciar los controles del formulario a su estado inicial
            cmbEmpresa.setSelectedIndex(0);
            cmbTitulo.removeAllItems();
            rbFisico.setSelected(true);
            txtPrecio.setText("");

            // 7. Refrescar la tabla para mostrar el nuevo registro al instante
            cargarTabla();

        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al guardar en la base de datos: " + e.getMessage());
        }
    
    }//GEN-LAST:event_BtnInsertarActionPerformed

    private void tblVideojuegosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVideojuegosMouseClicked
               
        
        //Actualizar
    int fila = tblVideojuegos.getSelectedRow();
        if (fila > -1) {
            String id = tblVideojuegos.getValueAt(fila, 0).toString();
            String titulo = tblVideojuegos.getValueAt(fila, 1).toString();
            String precio = tblVideojuegos.getValueAt(fila, 2).toString();
            String detalleJson = tblVideojuegos.getValueAt(fila, 3).toString();
            String empresa = tblVideojuegos.getValueAt(fila, 4).toString();

            cmbEmpresaAct.setSelectedItem(empresa);
            cmbTituloAct.setSelectedItem(titulo);
            txtPrecioAct.setText(precio);

            if (detalleJson.contains("\"tipo\": \"Digital\"")) {
                rbDigitalAct.setSelected(true);
            } else {
                rbFisicoAct.setSelected(true);
            }
          //Borrar
            txtIdVideojuego.setText(id);
        }
    
    
    
    }//GEN-LAST:event_tblVideojuegosMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
                                                   
        int filaSeleccionada = tblVideojuegos.getSelectedRow();

        if (filaSeleccionada > -1) {
            if (cmbEmpresaAct.getSelectedItem() == null || cmbTituloAct.getSelectedItem() == null
                    || cmbEmpresaAct.getSelectedIndex() <= 0 || cmbTituloAct.getSelectedIndex() <= 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Por favor, seleccione una empresa y un videojuego válido.", "Datos incompletos", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!rbFisicoAct.isSelected() && !rbDigitalAct.isSelected()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Por favor, seleccione si el videojuego es Físico o Digital.", "Tipo no seleccionado", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int idVideojuego = Integer.parseInt(tblVideojuegos.getValueAt(filaSeleccionada, 0).toString());
                String empresaDev = String.valueOf(cmbEmpresaAct.getSelectedItem());
                String titulo = String.valueOf(cmbTituloAct.getSelectedItem());
                String tipo = rbFisicoAct.isSelected() ? "Físico" : "Digital";

                int precioConIva = obtenerPrecioConIva(titulo);
                String detalleJson = obtenerDetalleJson(titulo, tipo);

                // 1. Guardar cambios en MySQL
                bd.DAOVideojuego dao = new bd.DAOVideojuego();
                dao.actualizarVideojuego(idVideojuego, titulo, precioConIva, detalleJson, empresaDev);

                // 2. Actualizar las celdas en la tabla
                tblVideojuegos.setValueAt(titulo, filaSeleccionada, 1);
                tblVideojuegos.setValueAt(precioConIva, filaSeleccionada, 2);
                tblVideojuegos.setValueAt(detalleJson, filaSeleccionada, 3);
                tblVideojuegos.setValueAt(empresaDev, filaSeleccionada, 4);

                javax.swing.JOptionPane.showMessageDialog(this, "Se actualizó correctamente", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (java.sql.SQLException e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al actualizar en la base de datos: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No seleccionó ninguna fila para actualizar", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
                                               
        String idTexto = txtIdVideojuego.getText().trim();
        int filaSeleccionada = tblVideojuegos.getSelectedRow();

        if (idTexto.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Debes seleccionar un videojuego de la tabla para borrar.",
                "Advertencia",
                javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        //PoPuPs de Interrogacion y Confirmacion
        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de que desea eliminar el videojuego con ID " + idTexto + "?",
            "Confirmar eliminación",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
            try {
                int idVideojuego = Integer.parseInt(idTexto);

                // 1. Eliminar en MySQL
                bd.DAOVideojuego dao = new bd.DAOVideojuego();
                dao.borrarVideojuego(idVideojuego);

                // 2. Eliminar la fila de la tabla visual (si está seleccionada) o recargar
                if (filaSeleccionada > -1) {
                    oModelo.removeRow(filaSeleccionada);
                    tblVideojuegos.clearSelection();
                } else {
                    cargarTabla();
                }

                // 3. Limpiar id y los controles de Actualizar
                txtIdVideojuego.setText("");
                cmbEmpresaAct.setSelectedIndex(0);
                cmbTituloAct.removeAllItems();
                rbFisicoAct.setSelected(true);

                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Videojuego eliminado correctamente.",
                    "Éxito",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch (java.sql.SQLException e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al eliminar en la base de datos: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
                                           

    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
                                             
        String empresaFiltro = txtEmpresaBuscar.getText().trim().toLowerCase();
        String tituloFiltro = txtVideojuegoBuscar.getText().trim().toLowerCase();

        boolean filtrarFisico = rbFisicoBuscar.isSelected();
        boolean filtrarDigital = rbDigitalBuscar.isSelected();

        try {
            bd.DAOVideojuego dao = new bd.DAOVideojuego();
            java.util.List<Model.Videojuego> lista = dao.listarVideojuegos();

            oModelo.setRowCount(0); // Limpiar filas actuales de la tabla

            for (Model.Videojuego v : lista) {
                String titulo = v.getTitulo_videojuego().toLowerCase();
                String empresa = v.getEmpresa_dev_videojuego().toLowerCase();
                String detalleJson = v.getDetalle_videojuego();

                boolean coincideEmpresa = empresaFiltro.isEmpty() || empresa.contains(empresaFiltro);
                boolean coincideTitulo = tituloFiltro.isEmpty() || titulo.contains(tituloFiltro);

                boolean coincideTipo = true;
                if (filtrarFisico) {
                    coincideTipo = detalleJson.contains("\"tipo\": \"Físico\"");
                } else if (filtrarDigital) {
                    coincideTipo = detalleJson.contains("\"tipo\": \"Digital\"");
                }

                if (coincideEmpresa && coincideTitulo && coincideTipo) {
                    oModelo.addRow(new Object[]{
                        v.getId_videojuego(),
                        v.getTitulo_videojuego(),
                        v.getPrecio_videojuego(),
                        v.getDetalle_videojuego(),
                        v.getEmpresa_dev_videojuego()
                    });
                }
            }

            if (oModelo.getRowCount() == 0) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No se encontraron videojuegos con esos criterios de búsqueda.",
                    "Sin resultados",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Error al buscar en la base de datos: " + e.getMessage(),
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    
    
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ManejoVideojuego().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnInsertar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnVolver;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cmbEmpresa;
    private javax.swing.JComboBox<String> cmbEmpresaAct;
    private javax.swing.JComboBox<String> cmbTitulo;
    private javax.swing.JComboBox<String> cmbTituloAct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTabbedPane pnlInsertar;
    private javax.swing.JRadioButton rbDigital;
    private javax.swing.JRadioButton rbDigitalAct;
    private javax.swing.JRadioButton rbDigitalBuscar;
    private javax.swing.JRadioButton rbFisico;
    private javax.swing.JRadioButton rbFisicoAct;
    private javax.swing.JRadioButton rbFisicoBuscar;
    private javax.swing.JTable tblVideojuegos;
    private javax.swing.JTextField txtDetalle;
    private javax.swing.JTextField txtEmpresaBuscar;
    private javax.swing.JTextField txtIdVideojuego;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtPrecioAct;
    private javax.swing.JTextField txtVideojuegoBuscar;
    // End of variables declaration//GEN-END:variables
}
