-- (a)
CREATE DATABASE Problem3
GO

USE Problem3
GO

CREATE TABLE Position
(
Id INT NOT NULL IDENTITY PRIMARY KEY,
[Name] NVARCHAR(200) NOT NULL 
)

CREATE TABLE Employee
(
Id INT NOT NULL IDENTITY PRIMARY KEY,
FullName NVARCHAR(500),
PositionId INT NOT NULL FOREIGN KEY REFERENCES Position(Id),
HomeAddress NVARCHAR(500)
)

CREATE TABLE Store
(
Id INT NOT NULL IDENTITY PRIMARY KEY,
[Name] NVARCHAR(200) NOT NULL,
[Location] NVARCHAR(500) NOT NULL
)

CREATE TABLE Schedule
(
EmployeeId INT NOT NULL FOREIGN KEY REFERENCES Employee(Id),
StoreId INT NOT NULL FOREIGN KEY REFERENCES Store(Id),
WorkDate DATE NOT NULL,
ArriveDateTime DATETIME NULL,
LeaveDateTime DATETIME NULL,
PRIMARY KEY(EmployeeId, StoreId, WorkDate)
)

-- (b)
SELECT a.FullName, SUM(DATEDIFF(HOUR, ArriveDateTime, LeaveDateTime)) 'WorkedHours'
FROM Employee a
JOIN Schedule b ON a.Id = b.EmployeeId
WHERE MONTH(WorkDate) = MONTH(GETDATE()) - 1
GROUP BY a.Id, a.FullName

-- (c)
SELECT a.StoreId, a.WorkDate, b.FullName
FROM Schedule a
LEFT JOIN 
(SELECT a.Id, FullName 
FROM Employee a
JOIN dbo.Position b ON a.PositionId = b.Id
WHERE b.Name = N'Manager') b ON a.EmployeeId = b.Id
WHERE a.WorkDate BETWEEN GETDATE() AND DATEADD(DAY, 7, GETDATE())

