export function TopBar() {
  const topBarStyle = {
    backgroundColor: "#333333",
    width: "calc(100% - 310px)",
    height: "80px",
    color: "#fff",
    boxShadow: "0px 4px 12px rgba(0, 0, 0, 0.2)",
    fontSize: "36px",
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
    borderRadius: "15px",
    paddingLeft: "30px",
    position: "fixed",
    top: 0,
    left: "290px",
    marginBottom: "20px",
    marginTop: "20px",
    zIndex: 999,
  };

  const topBarImageStyle = {
    width: "50px",
    borderRadius: "50%",
    marginRight: "20px",
  };

  return (
    <div style={topBarStyle}>
      <h1 style={{ margin: 0 }}>Admin Dashboard</h1>
      <div
        style={{
          display: "flex",
          alignItems: "center",
          justifyContent: "flex-end",
        }}
      >
        <img
          src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1r104bamd1_kCdG3cElkuPL7N3HJjjw1frNaE46aWp8tX2EjLvxCiFhzK2rYVm9OiThw&usqp=CAU"
          alt="Admin"
          style={topBarImageStyle}
        />
      </div>
    </div>
  );
}
