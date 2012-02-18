package torture

abstract class Operand

class Reg extends Operand
{
  var allocated = false
  var hwreg = new HWReg("-", false, false)

  override def toString = hwreg.toString
}

class RegNeedsAlloc(
  val hwrp: HWRegPool,
  val filter: (HWReg) => Boolean,
  val alloc: (HWReg) => Unit,
  val free: (HWReg) => Unit) extends Reg

class Imm(imm: Int) extends Operand
{
  override def toString = imm.toString
}

class BaseImm(base: String, imm: Int) extends Operand
{
  override def toString =
  {
    if (imm == 0) base
    else if (imm < 0) base + imm.toString
    else base + "+" + imm.toString
  }
}

class RegImm(base: Reg, imm: Int) extends Operand
{
  override def toString = imm.toString + "(" + base + ")"
}

class Label(val label: String) extends Operand
{
  override def toString = label
}

object Imm
{
  def apply(imm: Int) = new Imm(imm)
}

object BaseImm
{
  def apply(base: String, imm: Int) = new BaseImm(base, imm)
}

object RegImm
{
  def apply(base: Reg, imm: Int) = new RegImm(base, imm)
}

object Label
{
  def apply(label: String) = new Label(label)
}